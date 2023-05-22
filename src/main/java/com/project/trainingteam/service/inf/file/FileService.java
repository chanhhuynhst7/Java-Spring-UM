package com.project.trainingteam.service.inf.file;

import com.project.trainingteam.dto.file.FileDto;
import com.project.trainingteam.entities.file.File;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface FileService {
    FileDto saveFile(@Param("letterId") Long letterId , @Param("groupLetterCode") String groupLetterCode, MultipartFile file) throws Exception;

    File downloadFile(@Param("id")Long id);

    List<File> savedMultiFile(MultipartFile[] multipartFiles,Long id) throws Exception;


//    List<FileDto> findListFileByGroupLetterCodeAndLetterId(@Param("groupLetterCode")String groupLetterCode , @Param("letterId") Long letterId) throws Exception;

}
