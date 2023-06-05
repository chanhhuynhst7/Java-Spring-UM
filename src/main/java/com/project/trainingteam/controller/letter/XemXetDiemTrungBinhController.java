package com.project.trainingteam.controller.letter;

import com.project.trainingteam.dto.letter.XemXetDiemTrungBinhDto;

import com.project.trainingteam.entities.letter.XemXetDiemTrungBinh;
import com.project.trainingteam.service.inf.letter.XemXetDiemTrungBinhService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("api/xem-xet-diem-trung-binh")
public class XemXetDiemTrungBinhController {

    private XemXetDiemTrungBinhService xemXetDiemTrungBinhService;

    @PostMapping(value = "/create/{groupLetterName}")
    public ResponseEntity<XemXetDiemTrungBinhDto> createdXemXetDiemTrungBinh(@PathVariable("groupLetterName") String groupLetterName,
                                                                             @RequestParam(value = "files" , required = false) MultipartFile[] multipartFiles,
                                                                             @RequestPart("content") XemXetDiemTrungBinh req
    ) throws Exception {
        try {
            XemXetDiemTrungBinhDto createdXemXetDiemTrungBinh = xemXetDiemTrungBinhService.createdXemXetDiemTrungBinh(groupLetterName, multipartFiles,req);
            return new ResponseEntity<>(createdXemXetDiemTrungBinh, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e);
        }
    };

    @GetMapping("/all")
    public ResponseEntity<Page<XemXetDiemTrungBinhDto>> getAllXemXetDiemTrungBinh(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                        @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                        @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                        @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<XemXetDiemTrungBinhDto> xemXetDiemTrungBinhDtoPage = xemXetDiemTrungBinhService.getAllXemXetDiemTrungBinh(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(xemXetDiemTrungBinhDtoPage, HttpStatus.OK);
    };
}
