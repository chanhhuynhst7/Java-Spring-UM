package com.project.trainingteam.repo.inf.reason;


import com.project.trainingteam.entities.reason.ReasonChuyenGioThi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonChuyenGioThiRepo extends JpaRepository<ReasonChuyenGioThi,Long> {

    boolean existsByReasonChuyenGioThiName(String reasonChuyenGioThiName);

}
