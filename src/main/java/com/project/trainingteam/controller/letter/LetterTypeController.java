package com.project.trainingteam.controller.letter;


import com.project.trainingteam.dto.letter.LetterTypeDto;
import com.project.trainingteam.entities.letter.LetterType;
import com.project.trainingteam.entities.scoreboard.ScoreBoardType;
import com.project.trainingteam.service.inf.letter.LetterTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/letter-type")
public class LetterTypeController {

    private LetterTypeService letterTypeService;

    @PostMapping("/create")
    public ResponseEntity<LetterTypeDto> createdLetterTypeDto(@RequestBody LetterType letterType) throws Exception {
        LetterTypeDto createdLetterTypeDto = letterTypeService.createLetterType(letterType);
        return new ResponseEntity<>(createdLetterTypeDto, HttpStatus.CREATED);
    };


    @GetMapping("/find/{letterNameType}")
    public ResponseEntity<LetterTypeDto> findLetterTypeByLetterTypeName(@PathVariable("letterNameType") String letterNameType) throws Exception {
        LetterTypeDto findLetterTypeByLetterTypeName = letterTypeService.findLetterTypeByLetterTypeName(letterNameType);
        return new ResponseEntity<>(findLetterTypeByLetterTypeName, HttpStatus.CREATED);
    };

    @GetMapping("/all")
    public ResponseEntity<Page<LetterTypeDto>> getAllLetterType(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                 @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                 @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                                 @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterTypeDto> getAllLetterType = letterTypeService.getAllLetterType(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
        return new ResponseEntity<>(getAllLetterType, HttpStatus.OK);
    };

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedGroupLetter(@PathVariable("id") Long id)throws Exception{
        letterTypeService.deletedLetterType(id);
        return new ResponseEntity<>("Deleted Thành Công",HttpStatus.OK);
    };

}
