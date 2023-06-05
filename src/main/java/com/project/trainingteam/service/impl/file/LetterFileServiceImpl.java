package com.project.trainingteam.service.impl.file;


import com.project.trainingteam.entities.file.LetterFile;
import com.project.trainingteam.repo.inf.file.LetterFileRepo;
import com.project.trainingteam.service.inf.file.LetterFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LetterFileServiceImpl implements LetterFileService {

    @Autowired
    private LetterFileRepo letterFileRepo;

    @Override
    @Transactional
    public List<LetterFile> savedMultiLetterFile(MultipartFile[] multipartFiles, Long letterId, String letterTypeName) throws Exception {
        List<LetterFile> fileUpLoad = new ArrayList<>();
        Arrays.stream(multipartFiles).forEach(file -> {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                String downloadUrl = "";
                LetterFile f = new LetterFile();
                f.setFileName(fileName);
                f.setFileType(file.getContentType());
                f.setData(file.getBytes());
                f.setLetterId(letterId);
                f.setLetterTypeName(letterTypeName);
                // Set the Letter object with the provided letterId
                LetterFile savedLetterFile = letterFileRepo.save(f);

                if(fileName.equals("")){
                    f.setDownloadUrl("");
                }else{
                    downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/api/letter-file/download/")
                            .path(String.valueOf(f.getId()))
                            .toUriString();
                    f.setDownloadUrl(downloadUrl);
                }
                savedLetterFile = letterFileRepo.save(savedLetterFile);

                fileUpLoad.add(savedLetterFile);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return fileUpLoad;
    }

    @Override
    public LetterFile downloadLetterFile(Long id) {
        return letterFileRepo.findById(id).get();
    }
}
