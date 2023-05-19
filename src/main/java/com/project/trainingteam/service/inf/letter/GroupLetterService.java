package com.project.trainingteam.service.inf.letter;

import com.project.trainingteam.dto.letter.GroupLetterDto;
import com.project.trainingteam.dto.user.FacultyDto;
import com.project.trainingteam.entities.letter.GroupLetter;
import com.project.trainingteam.entities.user.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface GroupLetterService {
    GroupLetterDto createdGroupLetter(GroupLetter req) throws Exception;

    GroupLetterDto updatedGroupLetter(GroupLetter req) throws Exception;

    Page<GroupLetterDto> getAllGroupLetter(Pageable pageable) throws Exception;

    String deletedGroupLetter(Long id);
}
