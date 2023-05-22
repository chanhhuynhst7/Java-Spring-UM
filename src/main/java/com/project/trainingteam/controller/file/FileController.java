package com.project.trainingteam.controller.file;

import com.project.trainingteam.dto.file.FileDto;
import com.project.trainingteam.entities.file.File;
import com.project.trainingteam.service.inf.file.FileService;
import lombok.AllArgsConstructor;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/file")
public class FileController {

    private FileService fileService;
//
//    @PostMapping("/upload/{groupLetterCode}/{letterId}")
//    public ResponseEntity<FileDto> uploadFile(@PathVariable("letterId") Long letterId , @PathVariable("groupLetterCode") String groupLetterCode,@RequestParam("file") MultipartFile file)throws Exception{
//        FileDto fileDto = fileService.saveFile(letterId,groupLetterCode,file);
//        return new ResponseEntity<>(fileDto, HttpStatus.CREATED);
//    }
//
//
    @PostMapping("/upload-multiple")
    public ResponseEntity<List<File>> uploadMultiFile(@RequestParam("file") MultipartFile[] multipartFiles,Long letterId)throws Exception{
        List<File> fileList = fileService.savedMultiFile(multipartFiles,letterId);
        return new ResponseEntity<>(fileList, HttpStatus.CREATED);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws Exception {
        File file = fileService.downloadFile(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(file.getFileType()));
        httpHeaders.set("Content-Disposition", "attachment; filename=\"" + file.getFileName() + "\"");
        ByteArrayResource content = new ByteArrayResource(file.getData());
        return ResponseEntity.ok().headers(httpHeaders).body(content);
    }

//    @GetMapping("/{groupLetterCode}/{letterId}")
//    public ResponseEntity<List<FileDto>> getListFile(@PathVariable("groupLetterCode") String groupLetterCode,@PathVariable("letterId")Long letterId) throws Exception {
//        List<FileDto> fileDtoList = fileService.findListFileByGroupLetterCodeAndLetterId(groupLetterCode,letterId);
//        return new ResponseEntity<>(fileDtoList,HttpStatus.OK);
//    }



}
