package com.project.trainingteam.repo.inf.letter;

import com.project.trainingteam.entities.letter.GroupLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupLetterRepo extends JpaRepository<GroupLetter,Long> {

    boolean existsByGroupLetterName(String groupLetterName);

    Optional<GroupLetter> findGroupLetterByGroupLetterCode(String groupLetterCode);

    GroupLetter findGroupLetterByGroupLetterName(String groupLetterName);

}
