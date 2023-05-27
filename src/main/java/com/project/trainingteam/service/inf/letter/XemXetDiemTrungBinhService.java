package com.project.trainingteam.service.inf.letter;



import com.project.trainingteam.dto.letter.XemXetDiemTrungBinhDto;

import com.project.trainingteam.entities.letter.XemXetDiemTrungBinh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface XemXetDiemTrungBinhService {

    XemXetDiemTrungBinhDto createdXemXetDiemTrungBinh(@Param("groupLetterName") String groupLetterName, MultipartFile[] multipartFiles, @RequestBody XemXetDiemTrungBinh req) throws Exception;


    Page<XemXetDiemTrungBinhDto> getAllXemXetDiemTrungBinh(Pageable pageable) throws Exception;
}
