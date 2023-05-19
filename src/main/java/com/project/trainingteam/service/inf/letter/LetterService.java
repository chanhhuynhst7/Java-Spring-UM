package com.project.trainingteam.service.inf.letter;

import com.project.trainingteam.dto.letter.LetterDto;
import com.project.trainingteam.dto.letter.SemesterDto;
import com.project.trainingteam.entities.letter.Letter;
import com.project.trainingteam.entities.letter.Semester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface LetterService {

    LetterDto createdLetter(@Param("groupLetterCode") String groupLetterCode,Letter req) throws Exception;

    LetterDto updatedLetter(Letter req) throws Exception;

    Page<LetterDto> getAllLetter(Pageable pageable) throws Exception;

    Page<LetterDto> getAllLetterUser(Pageable pageable)throws Exception;

    Page<LetterDto> getAllLetterFaculty(@Param("facultyName") String facultyName, Pageable pageable)throws Exception;

    LetterDto getLetterById(Long id) throws Exception;



    String deletedLetter(Long id);

}
