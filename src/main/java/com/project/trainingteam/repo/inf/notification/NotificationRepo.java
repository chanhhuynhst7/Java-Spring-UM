package com.project.trainingteam.repo.inf.notification;

import com.project.trainingteam.entities.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Long> {

    @Query("SELECT n FROM Notification n WHERE n.id = :id")
    Notification findNotificationById(Long id);


    @Query("SELECT n FROM Notification n WHERE :username NOT MEMBER OF n.seenByUser")
    List<Notification> findUnseenNewsByUser(String username);
}
