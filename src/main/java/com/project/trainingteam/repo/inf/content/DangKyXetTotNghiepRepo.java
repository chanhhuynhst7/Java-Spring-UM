package com.project.trainingteam.repo.inf.content;

import com.project.trainingteam.entities.content.DangKyXetTotNghiep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DangKyXetTotNghiepRepo extends JpaRepository<DangKyXetTotNghiep,Long> {
}
