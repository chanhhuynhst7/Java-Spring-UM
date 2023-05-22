package com.project.trainingteam.service.inf.letter;

import com.project.trainingteam.dto.letter.LetterDto;
import com.project.trainingteam.dto.letter.SemesterDto;
import com.project.trainingteam.entities.content.DangKyXetTotNghiep;
import com.project.trainingteam.entities.letter.Letter;
import com.project.trainingteam.entities.letter.Semester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface LetterService {

    LetterDto createdLetter(@Param("groupLetterName") String groupLetterName, MultipartFile[] multipartFiles) throws Exception;

    LetterDto updatedLetter(Letter req, MultipartFile[] multipartFiles) throws Exception;

    Page<LetterDto> getAllLetter(Pageable pageable) throws Exception;

    Page<LetterDto> getAllLetterUser(Pageable pageable)throws Exception;

    Page<LetterDto> getAllLetterFaculty(@Param("facultyName") String facultyName, Pageable pageable)throws Exception;

    LetterDto getLetterById(Long id) throws Exception;

    String deletedLetter(Long id);

    List<LetterDto> getListLetter();

}
