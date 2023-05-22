package com.project.trainingteam.service.impl.letter;


import com.project.trainingteam.dto.letter.LetterDto;
import com.project.trainingteam.entities.content.DangKyXetTotNghiep;
import com.project.trainingteam.entities.file.File;
import com.project.trainingteam.entities.letter.GroupLetter;
import com.project.trainingteam.entities.letter.Letter;
import com.project.trainingteam.entities.user.User;
import com.project.trainingteam.repo.inf.content.DangKyXetTotNghiepRepo;
import com.project.trainingteam.repo.inf.file.FileRepo;
import com.project.trainingteam.repo.inf.letter.GroupLetterRepo;
import com.project.trainingteam.repo.inf.letter.LetterRepo;
import com.project.trainingteam.repo.inf.user.UserRepo;
import com.project.trainingteam.service.inf.content.DangKyXetTotNghiepService;
import com.project.trainingteam.service.inf.file.FileService;
import com.project.trainingteam.service.inf.letter.LetterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LetterServiceImpl implements LetterService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LetterRepo letterRepo;

    @Autowired
    private GroupLetterRepo groupLetterRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FileRepo fileRepo;

    @Autowired
    private FileService fileService;

    @Autowired
    private DangKyXetTotNghiepRepo dangKyXetTotNghiepRepo;

    @Override
    public LetterDto createdLetter(String groupLetterName, MultipartFile[] multipartFiles) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Optional<User> inforUser = userRepo.findByUsername(currentName);
        if (inforUser != null) {
            Letter letter = new Letter();
            letter.setUsername(inforUser.get().getUsername());
            letter.setFullname(inforUser.get().getFullname());
            letter.setClassUser(inforUser.get().getClassUser());
            letter.setFacultyName(inforUser.get().getFacultyName());
            letter.setMajor(inforUser.get().getMajor());
            letter.setPhone(inforUser.get().getPhone());
            letter.setGroupLetterName(groupLetterName);
            // Save the Letter object
            Letter savedLetter = letterRepo.save(letter);

            // Pass the generated letterId to savedMultiFile
            List<File> file = fileService.savedMultiFile(multipartFiles, savedLetter.getId());

            // Update the savedLetter with the file association
            savedLetter.setFile(file);

            savedLetter = letterRepo.save(savedLetter);

            return modelMapper.map(savedLetter, LetterDto.class);
        } else {
            throw new Exception("Hết lượt tạo mới");
        }
    };



    //chưa hoàn thành
    @Override
    public LetterDto updatedLetter(Letter req,MultipartFile[] multipartFiles) throws Exception {
        Letter letter = letterRepo.findById(req.getId()).get();
        if(letter != null){
            List<File> file = fileService.savedMultiFile(multipartFiles, letter.getId());
            // Update the savedLetter with the file association
            letter.setFile(file);
            Letter savedLetter = letterRepo.save(letter);
            return modelMapper.map(savedLetter, LetterDto.class);
        }else {
            throw new Exception("Không thể update Letter");
        }

    };

    @Override
    public Page<LetterDto> getAllLetter(Pageable pageable) throws Exception {
        Page<Letter> letterPage = letterRepo.findAll(pageable);
        List<Letter> letterList = letterPage.getContent();
        List<LetterDto> letterDtoList = letterList.stream().map((result) -> modelMapper.map(result,LetterDto.class)).collect(Collectors.toList());
        if(letterDtoList != null){
            return new PageImpl<>(letterDtoList,pageable,letterPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Letter");
        }
    };

    @Override
    public Page<LetterDto> getAllLetterUser(Pageable pageable) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Page<Letter> letterPage = letterRepo.findLetterByUserName(currentName,pageable);
        List<Letter> letterList = letterPage.getContent();
        List<LetterDto> letterDtoList = letterList.stream().map((result) -> modelMapper.map(result,LetterDto.class)).collect(Collectors.toList());
        if(letterDtoList != null){
            return new PageImpl<>(letterDtoList,pageable,letterPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Letter");
        }
    };

    @Override
    public Page<LetterDto> getAllLetterFaculty(String facultyName, Pageable pageable) throws Exception {
        Page<Letter> letterPage = letterRepo.findLetterByFacultyName(facultyName,pageable);
        List<Letter> letterList = letterPage.getContent();
        List<LetterDto> letterDtoList = letterList.stream().map((result) -> modelMapper.map(result,LetterDto.class)).collect(Collectors.toList());
        if(letterDtoList != null){
            return new PageImpl<>(letterDtoList,pageable,letterPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Semester");
        }


    };

    @Override
    public LetterDto getLetterById(Long letterId) throws Exception {
        Letter letter = letterRepo.findById(letterId).get();
        if(letter != null){
            return modelMapper.map(letter, LetterDto.class);
        }else{
            throw new Exception("không tìm thấy Letter");
        }
    };


    @Override
    public String deletedLetter(Long letterId) {
        letterRepo.deleteById(letterId);
        return "Delete thành Công";
    }

    @Override
    public List<LetterDto> getListLetter() {
        List<Letter> letterList = letterRepo.findAllLetter();
        return letterList.stream().map((result) -> modelMapper.map(result,LetterDto.class)).collect(Collectors.toList());
    }


}
