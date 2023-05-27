package com.project.trainingteam.repo.inf.letter;


import com.project.trainingteam.entities.letter.XemXetDiemTrungBinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface XemXetDiemTrungBinhRepo extends JpaRepository<XemXetDiemTrungBinh,Long> {

    @Query("SELECT COUNT(x.id) as check " +
            "FROM XemXetDiemTrungBinh  x " +
            "WHERE x.username = :username AND x.groupLetterName = :groupLetterName AND x.status != 2")
    Integer countXemXetDiemTrungBinhWithGroupLetterAndStatus0And1(String username, String groupLetterName);
}
