package com.project.trainingteam.service.inf.reason;

import com.project.trainingteam.dto.reason.ReasonChuyenGioThiDto;
import com.project.trainingteam.dto.reason.ReasonDangKyDuThiDto;
import com.project.trainingteam.entities.reason.ReasonChuyenGioThi;
import com.project.trainingteam.entities.reason.ReasonDangKyDuThi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ReasonDangKyDuThiService {
    ReasonDangKyDuThiDto createdReasonDangKyDuThi(ReasonDangKyDuThi req) throws Exception;

    ReasonDangKyDuThiDto updatedReasonDangKyDuThi(ReasonDangKyDuThi req) throws Exception;

    Page<ReasonDangKyDuThiDto> getAllReasonDangKyDuThi(Pageable pageable) throws Exception;

    String deletedReasonDangKyDuThi(Long id);
}
