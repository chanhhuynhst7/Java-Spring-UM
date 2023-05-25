package com.project.trainingteam.service.inf.reason;

import com.project.trainingteam.dto.reason.ReasonChuyenGioThiDto;
import com.project.trainingteam.entities.reason.ReasonChuyenGioThi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ReasonChuyenGioThiService {

    ReasonChuyenGioThiDto createdReasonChuyenGioThi(ReasonChuyenGioThi req) throws Exception;

    ReasonChuyenGioThiDto updatedReasonChuyenGioThi(ReasonChuyenGioThi req) throws Exception;

    Page<ReasonChuyenGioThiDto> getAllReasonChuyenGioThi(Pageable pageable) throws Exception;

    String deletedReasonChuyenGioThi(Long id);
}
