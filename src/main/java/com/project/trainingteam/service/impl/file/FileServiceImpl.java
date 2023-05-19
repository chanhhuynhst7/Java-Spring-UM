package com.project.trainingteam.service.impl.file;

import com.project.trainingteam.dto.file.FileDto;
import com.project.trainingteam.entities.file.File;
import com.project.trainingteam.entities.letter.GroupLetter;
import com.project.trainingteam.repo.inf.file.FileRepo;
import com.project.trainingteam.repo.inf.letter.GroupLetterRepo;
import com.project.trainingteam.service.inf.file.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileRepo fileRepo;

    @Autowired
    private GroupLetterRepo groupLetterRepo;

    @Override
    public FileDto saveFile(Long letterId, String groupLetterCode,MultipartFile f) throws Exception {
        String fileName = StringUtils.cleanPath(f.getOriginalFilename());
        String downloadUrl = "";
        Optional<GroupLetter> groupLetterName = groupLetterRepo.findGroupLetterByGroupLetterCode(groupLetterCode);
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }
            File file = new File();
            file.setLetterId(letterId);
            file.setGroupLetterName(groupLetterName.get().getGroupLetterName());
            file.setFileName(fileName);
            file.setFileType(f.getContentType());
            file.setData(f.getBytes());
            File savedFile =  fileRepo.save(file);

            downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/file/download/")
                    .path(String.valueOf(file.getId()))
                    .toUriString();

            FileDto fileDto = new FileDto();
            fileDto.setId(savedFile.getId());
            fileDto.setLetterId(savedFile.getLetterId());
            fileDto.setGroupLetterName(savedFile.getGroupLetterName());
            fileDto.setFileName(savedFile.getFileName());
            fileDto.setFileType(savedFile.getFileType());
            fileDto.setDownloadUrl(downloadUrl);
            return fileDto;
        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    @Override
    public File downloadFile(Long id) {
        return fileRepo.findById(id).get();
    }

    @Override
    public List<FileDto> savedMultiFile(Long letterId, String groupLetterCode, MultipartFile[] multipartFiles) throws Exception {
        Optional<GroupLetter> groupLetterName = groupLetterRepo.findGroupLetterByGroupLetterCode(groupLetterCode);
        List<FileDto> fileUpLoad = new ArrayList<>();
        Arrays.stream(multipartFiles).forEach(file -> {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                String downloadUrl = "";
                File f = new File();
                f.setLetterId(letterId);  // Set the letterId property
                f.setGroupLetterName(groupLetterName.get().getGroupLetterName());
                f.setFileName(fileName);
                f.setFileType(file.getContentType());
                f.setData(file.getBytes());
                File savedFile = fileRepo.save(f);
                downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/file/download/")
                        .path(String.valueOf(f.getId()))
                        .toUriString();

                FileDto fileDto = new FileDto();
                fileDto.setId(savedFile.getId());
                fileDto.setLetterId(savedFile.getLetterId());
                fileDto.setGroupLetterName(savedFile.getGroupLetterName());
                fileDto.setFileName(savedFile.getFileName());
                fileDto.setFileType(savedFile.getFileType());
                fileDto.setDownloadUrl(downloadUrl);
                fileUpLoad.add(fileDto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return fileUpLoad;
    }
//    @Override
//    public List<FileDto> findListFileByGroupLetterCodeAndLetterId(String groupLetterCode, Long letterId) throws Exception {
//
//        List<File> fileList = fileRepo.findListFileByGroupLetterCodeAndLetterId(groupLetterCode,letterId);
//        if(fileList != null){
//            return fileList.stream().map((tech) -> modelMapper.map(tech, FileDto.class)).collect(Collectors.toList());
//        }else{
//            throw new Exception("Không tìm thấy File");
//        }
//    };


}
