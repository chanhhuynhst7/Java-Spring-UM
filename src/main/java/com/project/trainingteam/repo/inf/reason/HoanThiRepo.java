package com.project.trainingteam.repo.inf.reason;

import com.project.trainingteam.entities.reason.HoanThi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoanThiRepo extends JpaRepository<HoanThi,Long> {
}
