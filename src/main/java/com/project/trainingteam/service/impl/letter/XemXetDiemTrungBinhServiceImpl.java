package com.project.trainingteam.service.impl.letter;


import com.project.trainingteam.dto.letter.XemXetDiemTrungBinhDto;
import com.project.trainingteam.entities.file.LetterFile;
import com.project.trainingteam.entities.letter.XemXetDiemTrungBinh;
import com.project.trainingteam.entities.user.User;
import com.project.trainingteam.repo.inf.letter.XemXetDiemTrungBinhRepo;
import com.project.trainingteam.repo.inf.user.UserRepo;
import com.project.trainingteam.service.inf.file.LetterFileService;
import com.project.trainingteam.service.inf.letter.XemXetDiemTrungBinhService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class XemXetDiemTrungBinhServiceImpl implements XemXetDiemTrungBinhService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private XemXetDiemTrungBinhRepo xemXetDiemTrungBinhRepo;

    @Autowired
    private LetterFileService letterFileService;


    @Override
    public XemXetDiemTrungBinhDto createdXemXetDiemTrungBinh(String groupLetterName, MultipartFile[] multipartFiles, XemXetDiemTrungBinh req) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Optional<User> inforUser = userRepo.findByUsername(currentName);
        Integer check = xemXetDiemTrungBinhRepo.countXemXetDiemTrungBinhWithGroupLetterAndStatus0And1(currentName,groupLetterName);
        if(check < 1){
            if (inforUser != null) {
                XemXetDiemTrungBinh xemXetDiemTrungBinh = new XemXetDiemTrungBinh();
                xemXetDiemTrungBinh.setUsername(inforUser.get().getUsername());
                xemXetDiemTrungBinh.setFullName(inforUser.get().getFullname());
                xemXetDiemTrungBinh.setClassUser(inforUser.get().getClassUser());
                xemXetDiemTrungBinh.setFacultyName(inforUser.get().getFacultyName());
                xemXetDiemTrungBinh.setMajor(inforUser.get().getMajor());
                xemXetDiemTrungBinh.setPhone(inforUser.get().getPhone());
                xemXetDiemTrungBinh.setGroupLetterName(groupLetterName);
                xemXetDiemTrungBinh.setSemesterName(req.getSemesterName());
                xemXetDiemTrungBinh.setPointType(req.getPointType());
                xemXetDiemTrungBinh.setReason(req.getReason());

                // Save the XemXetDiemTrungBinh object
                XemXetDiemTrungBinh savedXemXetDiemTrungBinh = xemXetDiemTrungBinhRepo.save(xemXetDiemTrungBinh);

                // Pass the generated xemXetDiemTrungBinhId to savedMulLetterFile
                List<LetterFile> letterFileList = letterFileService.savedMultiLetterFile(multipartFiles, savedXemXetDiemTrungBinh.getId(),savedXemXetDiemTrungBinh.getGroupLetterName());

                XemXetDiemTrungBinhDto xemXetDiemTrungBinhDto = new XemXetDiemTrungBinhDto();
                xemXetDiemTrungBinhDto.setUsername(savedXemXetDiemTrungBinh.getUsername());
                xemXetDiemTrungBinhDto.setFullName(savedXemXetDiemTrungBinh.getFullName());
                xemXetDiemTrungBinhDto.setClassUser(savedXemXetDiemTrungBinh.getClassUser());
                xemXetDiemTrungBinhDto.setFacultyName(savedXemXetDiemTrungBinh.getFacultyName());
                xemXetDiemTrungBinhDto.setMajor(savedXemXetDiemTrungBinh.getMajor());
                xemXetDiemTrungBinhDto.setPhone(savedXemXetDiemTrungBinh.getPhone());
                xemXetDiemTrungBinhDto.setGroupLetterName(savedXemXetDiemTrungBinh.getGroupLetterName());
                xemXetDiemTrungBinhDto.setSemesterName(savedXemXetDiemTrungBinh.getSemesterName());
                xemXetDiemTrungBinhDto.setPointType(savedXemXetDiemTrungBinh.getPointType());
                xemXetDiemTrungBinhDto.setReason(savedXemXetDiemTrungBinh.getReason());
                xemXetDiemTrungBinhDto.setLetterFileList(letterFileList);
                return xemXetDiemTrungBinhDto;
            } else {
                throw new Exception("Không thể tạo  Đơn Xem Xét Điểm Trung Bình");
            }
        }else{
            throw new Exception("hết lược tạo mới");
        }
    }

    @Override
    public Page<XemXetDiemTrungBinhDto> getAllXemXetDiemTrungBinh(Pageable pageable) throws Exception {
        Page<XemXetDiemTrungBinh> xemXetDiemTrungBinhPage = xemXetDiemTrungBinhRepo.findAll(pageable);
        List<XemXetDiemTrungBinh> xemXetDiemTrungBinhList = xemXetDiemTrungBinhPage.getContent();
        List<XemXetDiemTrungBinhDto> xemXetDiemTrungBinhDtoList = xemXetDiemTrungBinhList.stream().map((result) -> modelMapper.map(result,XemXetDiemTrungBinhDto.class)).collect(Collectors.toList());
        if(xemXetDiemTrungBinhDtoList != null){
            return new PageImpl<>(xemXetDiemTrungBinhDtoList,pageable,xemXetDiemTrungBinhPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy danh sách đơn Xem Xét Điểm Trung Bình");
        }
    };
}
