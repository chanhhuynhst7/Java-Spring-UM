package com.project.trainingteam.controller.reason;

import com.project.trainingteam.dto.letter.SemesterDto;
import com.project.trainingteam.dto.reason.ReasonChuyenGioThiDto;
import com.project.trainingteam.entities.letter.Semester;
import com.project.trainingteam.entities.reason.ReasonChuyenGioThi;
import com.project.trainingteam.service.inf.reason.ReasonChuyenGioThiService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/reason-chuyen-gio-thi")
public class ReasonChuyenGioThiController {
    private ReasonChuyenGioThiService reasonChuyenGioThiService;

    @PostMapping("/create")
    public ResponseEntity<ReasonChuyenGioThiDto> createdReasonChuyenGioThi(@RequestBody ReasonChuyenGioThi req) throws Exception {
        ReasonChuyenGioThiDto createdReasonChuyenGioThi = reasonChuyenGioThiService.createdReasonChuyenGioThi(req);
        return new ResponseEntity<>(createdReasonChuyenGioThi, HttpStatus.CREATED);
    };

    @PutMapping("/update/{id}")
    public ResponseEntity<ReasonChuyenGioThiDto> updatedReasonChuyenGioThi(@PathVariable("id")Long id, @RequestBody ReasonChuyenGioThi req) throws Exception {
        req.setId(id);
        ReasonChuyenGioThiDto updatedReasonChuyenGioThi = reasonChuyenGioThiService.updatedReasonChuyenGioThi(req);
        return new ResponseEntity<>(updatedReasonChuyenGioThi, HttpStatus.CREATED);
    };

    @GetMapping("/all")
    public ResponseEntity<Page<ReasonChuyenGioThiDto>> getAllReasonChuyenGioThi(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                            @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                            @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                            @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<ReasonChuyenGioThiDto> reasonChuyenGioThiDtoPage = reasonChuyenGioThiService.getAllReasonChuyenGioThi(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
        return new ResponseEntity<>(reasonChuyenGioThiDtoPage, HttpStatus.OK);
    };

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedReasonChuyenGioThi(@PathVariable("id") Long id)throws Exception{
        reasonChuyenGioThiService.deletedReasonChuyenGioThi(id);
        return new ResponseEntity<>("Deleted Thành Công",HttpStatus.OK);
    };
}
