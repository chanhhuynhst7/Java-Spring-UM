package com.project.trainingteam.service.impl.content;

import com.project.trainingteam.entities.content.DangKyXetTotNghiep;
import com.project.trainingteam.repo.inf.content.DangKyXetTotNghiepRepo;
import com.project.trainingteam.service.inf.content.DangKyXetTotNghiepService;
import org.springframework.stereotype.Service;

@Service
public class DangKyXetTotNghiepImpl implements DangKyXetTotNghiepService {

    private DangKyXetTotNghiepRepo dangKyXetTotNghiepRepo;
    @Override
    public DangKyXetTotNghiep created(DangKyXetTotNghiep req, Long id) {
        DangKyXetTotNghiep dangKyXetTotNghiep = new DangKyXetTotNghiep();
        dangKyXetTotNghiep.setReason(req.getReason());
        dangKyXetTotNghiep.setLetterId(id);
        DangKyXetTotNghiep savedDangKyXetTotNghiep = dangKyXetTotNghiepRepo.save(dangKyXetTotNghiep);
        return savedDangKyXetTotNghiep;
    };
}
