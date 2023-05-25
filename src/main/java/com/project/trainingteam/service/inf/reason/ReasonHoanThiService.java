package com.project.trainingteam.service.inf.reason;

import com.project.trainingteam.dto.reason.ReasonDangKyDuThiDto;
import com.project.trainingteam.dto.reason.ReasonHoanThiDto;
import com.project.trainingteam.entities.reason.ReasonDangKyDuThi;
import com.project.trainingteam.entities.reason.ReasonHoanThi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ReasonHoanThiService {

    ReasonHoanThiDto createdReasonHoanThi(ReasonHoanThi req) throws Exception;

    ReasonHoanThiDto updatedReasonHoanThi(ReasonHoanThi req) throws Exception;

    Page<ReasonHoanThiDto> getAllReasonHoanThi(Pageable pageable) throws Exception;

    String deletedReasonHoanThi(Long id);
}
