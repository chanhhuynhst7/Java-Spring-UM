package com.project.trainingteam.repo.inf.file;


import com.project.trainingteam.entities.file.LetterFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterFileRepo extends JpaRepository<LetterFile,Long> {
}
