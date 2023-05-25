package com.project.trainingteam.repo.inf.reason;

import com.project.trainingteam.entities.reason.ReasonHoanThi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonHoanThiRepo extends JpaRepository<ReasonHoanThi,Long> {

    boolean existsByReasonHoanThiName(String reasonHoanThiName);

}
