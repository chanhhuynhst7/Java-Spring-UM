package com.project.trainingteam.service.inf.request;

import com.project.trainingteam.entities.request.ScoreBoardRequest;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreBoardRequestService {
    List<ScoreBoardRequest> createdListScoreBoardRequest(@Param("letterId") Long letterId,@Param("letterTypeId")Long letterTypeId, @Param("letterTypeName") String letterTypeName, ScoreBoardRequest[] scoreBoardRequests);

    List<ScoreBoardRequest> findScoreBoardRequestByLetterId(@Param("letterId") Long letterId);
}
