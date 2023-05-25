package com.project.trainingteam.repo.inf.reason;

import com.project.trainingteam.entities.reason.DangKyDuThi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DangKyDuThiRepo extends JpaRepository<DangKyDuThi,Long> {
}
