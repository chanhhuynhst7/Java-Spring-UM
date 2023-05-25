package com.project.trainingteam.controller.reason;

import com.project.trainingteam.dto.reason.ReasonDangKyDuThiDto;
import com.project.trainingteam.dto.reason.ReasonHoanThiDto;
import com.project.trainingteam.entities.reason.ReasonDangKyDuThi;
import com.project.trainingteam.entities.reason.ReasonHoanThi;
import com.project.trainingteam.service.inf.reason.ReasonHoanThiService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/reason-hoan-thi")
public class ReasonHoanThiController {

    private ReasonHoanThiService reasonHoanThiService;

    @PostMapping("/create")
    public ResponseEntity<ReasonHoanThiDto> createdReasonHoanThi(@RequestBody ReasonHoanThi req) throws Exception {
        ReasonHoanThiDto createdReasonHoanThi = reasonHoanThiService.createdReasonHoanThi(req);
        return new ResponseEntity<>(createdReasonHoanThi, HttpStatus.CREATED);
    };

    @PutMapping("/update/{id}")
    public ResponseEntity<ReasonHoanThiDto> updatedReasonHoanThi(@PathVariable("id")Long id, @RequestBody ReasonHoanThi req) throws Exception {
        req.setId(id);
        ReasonHoanThiDto updatedReasonHoanThi = reasonHoanThiService.updatedReasonHoanThi(req);
        return new ResponseEntity<>(updatedReasonHoanThi, HttpStatus.CREATED);
    };

    @GetMapping("/all")
    public ResponseEntity<Page<ReasonHoanThiDto>> getAllReasonHoanThi(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                              @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                              @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                                              @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<ReasonHoanThiDto> reasonHoanThiDtoPage = reasonHoanThiService.getAllReasonHoanThi(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
        return new ResponseEntity<>(reasonHoanThiDtoPage, HttpStatus.OK);
    };

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedReasonHoanThi(@PathVariable("id") Long id)throws Exception{
        reasonHoanThiService.deletedReasonHoanThi(id);
        return new ResponseEntity<>("Deleted Thành Công",HttpStatus.OK);
    };
}
