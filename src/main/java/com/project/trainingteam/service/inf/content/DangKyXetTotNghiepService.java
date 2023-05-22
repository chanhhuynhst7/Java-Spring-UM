package com.project.trainingteam.service.inf.content;

import com.project.trainingteam.entities.content.DangKyXetTotNghiep;
import org.springframework.stereotype.Service;

@Service
public interface DangKyXetTotNghiepService {

    DangKyXetTotNghiep created(DangKyXetTotNghiep req , Long id);

}
