package com.project.trainingteam.repo.inf.request;

import com.project.trainingteam.entities.request.ScoreBoardRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreBoardRequestRepo extends JpaRepository<ScoreBoardRequest,Long> {
}
