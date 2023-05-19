package com.project.trainingteam.repo.inf.file;

import com.project.trainingteam.dto.file.FileDto;
import com.project.trainingteam.entities.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepo extends JpaRepository<File,Long> {

//    @Query("SELECT f FROM File WHERE f.groupLetterCode = :groupLetterCode AND f.letterId = :letterId")
//    List<File> findListFileByGroupLetterCodeAndLetterId(String groupLetterCode,Long letterId);
}
