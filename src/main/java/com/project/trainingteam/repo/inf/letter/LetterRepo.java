package com.project.trainingteam.repo.inf.letter;

import com.project.trainingteam.entities.letter.Letter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LetterRepo extends JpaRepository<Letter,Long> {

//    @Query("SELECT COUNT(l.letterId) as check " +
//            "FROM Letter l JOIN GroupLetter g " +
//            "WHERE g.id = l.groupLetter.id AND l.username = :username AND g.groupLetterName = :groupLetterName")
//    Integer countLettersWithGroupLetter(String username, String groupLetterName);

    @Query("SELECT l FROM Letter l WHERE l.username = :username")
    Page<Letter>findLetterByUserName(String username, Pageable pageable);

    @Query("SELECT l FROM Letter l WHERE l.facultyName = :facultyName")
    Page<Letter>findLetterByFacultyName(String facultyName, Pageable pageable);

    @Query("SELECT l FROM Letter l")
    List<Letter>findAllLetter();

}
