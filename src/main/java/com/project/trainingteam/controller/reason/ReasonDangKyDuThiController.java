package com.project.trainingteam.controller.reason;

import com.project.trainingteam.dto.reason.ReasonChuyenGioThiDto;
import com.project.trainingteam.dto.reason.ReasonDangKyDuThiDto;
import com.project.trainingteam.entities.reason.ReasonChuyenGioThi;
import com.project.trainingteam.entities.reason.ReasonDangKyDuThi;
import com.project.trainingteam.service.inf.reason.ReasonDangKyDuThiService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/reason-dang-ky-du-thi")
public class ReasonDangKyDuThiController {

    private ReasonDangKyDuThiService reasonDangKyDuThiService;

    @PostMapping("/create")
    public ResponseEntity<ReasonDangKyDuThiDto> createdReasonDangKyDuThi(@RequestBody ReasonDangKyDuThi req) throws Exception {
        ReasonDangKyDuThiDto createdReasonDangKyDuThi = reasonDangKyDuThiService.createdReasonDangKyDuThi(req);
        return new ResponseEntity<>(createdReasonDangKyDuThi, HttpStatus.CREATED);
    };

    @PutMapping("/update/{id}")
    public ResponseEntity<ReasonDangKyDuThiDto> updatedReasonDangKyDuThi(@PathVariable("id")Long id, @RequestBody ReasonDangKyDuThi req) throws Exception {
        req.setId(id);
        ReasonDangKyDuThiDto updatedReasonDangKyDuThi = reasonDangKyDuThiService.updatedReasonDangKyDuThi(req);
        return new ResponseEntity<>(updatedReasonDangKyDuThi, HttpStatus.CREATED);
    };

    @GetMapping("/all")
    public ResponseEntity<Page<ReasonDangKyDuThiDto>> getAllReasonDangKyDuThi(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                                @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                                                @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<ReasonDangKyDuThiDto> reasonDangKyDuThiDtoPage = reasonDangKyDuThiService.getAllReasonDangKyDuThi(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
        return new ResponseEntity<>(reasonDangKyDuThiDtoPage, HttpStatus.OK);
    };

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedReasonDangKyDuThi(@PathVariable("id") Long id)throws Exception{
        reasonDangKyDuThiService.deletedReasonDangKyDuThi(id);
        return new ResponseEntity<>("Deleted Thành Công",HttpStatus.OK);
    };
}
