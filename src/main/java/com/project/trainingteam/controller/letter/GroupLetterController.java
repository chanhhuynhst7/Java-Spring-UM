package com.project.trainingteam.controller.letter;

import com.project.trainingteam.dto.letter.GroupLetterDto;
import com.project.trainingteam.entities.letter.GroupLetter;
import com.project.trainingteam.service.inf.letter.GroupLetterService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/group-letter")
public class GroupLetterController {
    private GroupLetterService groupLetterService;
    @PostMapping("/create")
    public ResponseEntity<GroupLetterDto> createdGroupLetter(@RequestBody GroupLetter req) throws Exception {
        GroupLetterDto createdGroupLetter = groupLetterService.createdGroupLetter(req);
        return new ResponseEntity<>(createdGroupLetter, HttpStatus.CREATED);
    };

    @PutMapping("/update/{id}")
    public ResponseEntity<GroupLetterDto> updatedGroupLetter(@PathVariable("id")Long id, @RequestBody GroupLetter req) throws Exception {
        req.setId(id);
        GroupLetterDto updatedGroupLetter = groupLetterService.updatedGroupLetter(req);
        return new ResponseEntity<>(updatedGroupLetter, HttpStatus.CREATED);
    };


    @GetMapping("/all")
    public ResponseEntity<Page<GroupLetterDto>> getAllGroupLetter(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                          @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                          @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                          @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<GroupLetterDto> groupLetter = groupLetterService.getAllGroupLetter(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
        return new ResponseEntity<>(groupLetter, HttpStatus.OK);
    };

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedGroupLetter(@PathVariable("id") Long id)throws Exception{
        groupLetterService.deletedGroupLetter(id);
        return new ResponseEntity<>("Deleted Thành Công",HttpStatus.OK);
    }
}
