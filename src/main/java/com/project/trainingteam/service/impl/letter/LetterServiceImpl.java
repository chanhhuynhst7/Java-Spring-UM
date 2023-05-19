package com.project.trainingteam.service.impl.letter;

import com.project.trainingteam.dto.letter.LetterDto;
import com.project.trainingteam.dto.letter.SemesterDto;
import com.project.trainingteam.dto.user.UserDto;
import com.project.trainingteam.entities.letter.GroupLetter;
import com.project.trainingteam.entities.letter.Letter;
import com.project.trainingteam.entities.letter.Semester;
import com.project.trainingteam.entities.user.User;
import com.project.trainingteam.repo.inf.letter.GroupLetterRepo;
import com.project.trainingteam.repo.inf.letter.LetterRepo;
import com.project.trainingteam.repo.inf.user.UserRepo;
import com.project.trainingteam.service.inf.letter.LetterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

    @Override
    public LetterDto createdLetter(String groupLetterCode ,Letter req) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Optional<User> inforUser = userRepo.findByUsername(currentName);
        Optional<GroupLetter> groupLetterName = groupLetterRepo.findGroupLetterByGroupLetterCode(groupLetterCode);
        Integer check = letterRepo.checkConditionLetter(inforUser.get().getUsername(),groupLetterName.get().getGroupLetterName());
        if(check < 2){
            if(inforUser !=  null){
                Letter letter = new Letter();
                letter.setGroupLetter(groupLetterName.get().getGroupLetterName());
                letter.setUsername(inforUser.get().getUsername());
                letter.setFullname(inforUser.get().getFullname());
                letter.setClassUser(inforUser.get().getClassUser());
                letter.setFacultyName(inforUser.get().getFacultyName());
                letter.setMajor(inforUser.get().getMajor());
                letter.setPhone(inforUser.get().getPhone());
                letter.setSemesterName(req.getSemesterName());
                letter.setReason(req.getReason());
                letter.setHandlePart(inforUser.get().getPositionName() + "-" + inforUser.get().getFacultyName());
                Letter savedLetter = letterRepo.save(letter);
                return modelMapper.map(savedLetter, LetterDto.class);
            }else{
                throw new Exception("Không tìm thấy thông tin User");
            }
        }else{
            throw new Exception("Bạn đã hết quyền tạo Letter");
        }
    };

    @Override
    public LetterDto updatedLetter(Letter req) throws Exception {
        Letter letter = letterRepo.findById(req.getId()).get();
        if(letter != null){
            letter.setProcessedDate(req.getProcessedDate());
            letter.setResultDate(req.getResultDate());
            letter.setNote(req.getNote());
            letter.setStatus(req.getStatus());
            letter.setResult(req.getResult());
            Letter savedLetter = letterRepo.save(letter);
            return modelMapper.map(savedLetter,LetterDto.class);
        }else{
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
            throw new Exception("Không tìm thấy List Semester");
        }
    }

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
            throw new Exception("Không tìm thấy List Semester");
        }
    }

    @Override
    public Page<LetterDto> getAllLetterFaculty(String facultyName,Pageable pageable) throws Exception {
        Page<Letter> letterPage = letterRepo.findLetterByFacultyName(facultyName,pageable);
        List<Letter> letterList = letterPage.getContent();
        List<LetterDto> letterDtoList = letterList.stream().map((result) -> modelMapper.map(result,LetterDto.class)).collect(Collectors.toList());
        if(letterDtoList != null){
            return new PageImpl<>(letterDtoList,pageable,letterPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Semester");
        }
    }

    @Override
    public LetterDto getLetterById(Long id) throws Exception {
        Letter letter = letterRepo.findById(id).get();
        if(letter != null){
            return modelMapper.map(letter, LetterDto.class);
        }else{
            throw new Exception("không tìm thấy Letter");
        }
    };


    @Override
    public String deletedLetter(Long id) {
        letterRepo.deleteById(id);
        return "Delete thành Công";
    }

    ;
}
