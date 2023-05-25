package com.project.trainingteam.repo.inf.reason;


import com.project.trainingteam.entities.reason.ChuyenGioThi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuyenGioThiRepo extends JpaRepository<ChuyenGioThi,Long> {
}
