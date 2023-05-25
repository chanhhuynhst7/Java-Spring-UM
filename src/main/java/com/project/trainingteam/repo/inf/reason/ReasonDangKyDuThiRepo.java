package com.project.trainingteam.repo.inf.reason;

import com.project.trainingteam.entities.reason.ReasonDangKyDuThi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonDangKyDuThiRepo extends JpaRepository<ReasonDangKyDuThi,Long> {

    boolean existsByReasonDangKyDuThiName(String reasonDangKyDuThiName);

}
