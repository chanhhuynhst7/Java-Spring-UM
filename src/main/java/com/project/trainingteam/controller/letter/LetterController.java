package com.project.trainingteam.controller.letter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.trainingteam.dto.letter.LetterDto;
import com.project.trainingteam.entities.letter.Letter;
import com.project.trainingteam.service.inf.letter.LetterService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataInput;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/letter")
public class LetterController {

    private LetterService letterService;


    @PostMapping(value = "/create/{groupLetterName}")
    public ResponseEntity<LetterDto> createdLetter(@PathVariable("groupLetterName") String groupLetterName,
                                                   @RequestParam("file") MultipartFile[] multipartFiles,
                                                   @RequestPart("content") Letter req
                                                   ) throws Exception {
        try {
            LetterDto createdLetter = letterService.createdLetter(groupLetterName, multipartFiles,req);
            return new ResponseEntity<>(createdLetter, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e);
        }
    };


    @PutMapping("/update/{id}")
    public ResponseEntity<LetterDto> updatedLetter(@PathVariable("id")Long id, @RequestBody Letter req) throws Exception {
        req.setId(id);
        LetterDto updatedLetter = letterService.updatedLetter(req);
        return new ResponseEntity<>(updatedLetter, HttpStatus.CREATED);
    };


    @GetMapping("/all")
    public ResponseEntity<Page<LetterDto>> getAllLetter(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                        @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                        @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                        @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDtoPage = letterService.getAllLetter(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDtoPage, HttpStatus.OK);
    };

    @GetMapping("/current")
    public ResponseEntity<Page<LetterDto>> getAllLetterUser(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                            @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                            @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDtoPage = letterService.getAllLetterUser(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDtoPage, HttpStatus.OK);
    };

    @GetMapping("/{id}")
    public ResponseEntity<LetterDto> getLetterById(@PathVariable("id") Long id) throws Exception {
        LetterDto letterDto = letterService.getLetterById(id);
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };

    @GetMapping("/don-da-nop")
    public ResponseEntity<Page<LetterDto>> getUserLetterByStatus0(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                  @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                  @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                  @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getUserLetterByStatus0(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };

    @GetMapping("/don-dang-xu-li")
    public ResponseEntity<Page<LetterDto>> getUserLetterByStatus1(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                  @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                  @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                  @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getUserLetterByStatus1(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };

    @GetMapping("/don-da-xu-li")
    public ResponseEntity<Page<LetterDto>> getUserLetterByStatus2(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                  @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                  @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                  @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getUserLetterByStatus2(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };

    //////////////////////////////////////////////////////////////////////
    @GetMapping("/faculty/{facultyName}")
    public ResponseEntity<Page<LetterDto>> getLetterByFacultyName(@PathVariable("facultyName") String facultyName, @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                  @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                  @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                  @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getAllLetterFaculty(facultyName, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);

    };

    @GetMapping("/faculty/don-chua-xu-li")
    public ResponseEntity<Page<LetterDto>> getFacultyLetterByFacultyNameAndStatus0And1(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                  @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                  @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                  @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getFacultyLetterByFacultyNameAndStatus0And1(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };

    @GetMapping("/faculty/don-da-nop")
    public ResponseEntity<Page<LetterDto>> getFacultyLetterByFacultyNameAndStatus0(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                       @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                                       @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                                       @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getFacultyLetterByFacultyNameAndStatus0(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };

    @GetMapping("/faculty/don-dang-xu-li")
    public ResponseEntity<Page<LetterDto>> getFacultyLetterByFacultyNameAndStatus1(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                       @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                                       @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                                       @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getFacultyLetterByFacultyNameAndStatus1(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };

    @GetMapping("/faculty/don-da-xu-li")
    public ResponseEntity<Page<LetterDto>> getFacultyLetterByFacultyNameAndStatus2(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                       @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                                       @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                                       @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getFacultyLetterByFacultyNameAndStatus2(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };


    /////////////////////////////////////////////////////////////////////////////////////
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedLetter(@PathVariable("id") Long id) throws Exception {
        letterService.deletedLetter(id);
        return new ResponseEntity<>("Deleted Thành Công", HttpStatus.OK);
    };


    @GetMapping("/test")
    public ResponseEntity<List<LetterDto>> test() {
        List<LetterDto> letterDto = letterService.getListLetter();
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };

}
