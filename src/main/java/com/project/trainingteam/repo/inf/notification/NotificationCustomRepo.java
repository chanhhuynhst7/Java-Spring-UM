package com.project.trainingteam.repo.inf.notification;

import com.project.trainingteam.entities.notification.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NotificationCustomRepo {

    Page<Notification> searchNotificationByFacultyName(String notificationTitle,String notificationContent,String categoryName,String facultyName,Date startedDate,Date endedDate,Pageable pageable);


    Page<Notification> searchNotificationByDepartCenterName(String notificationTitle, String notificationContent, String categoryName, String departCenterName, Date startedDate, Date endedDate,Pageable pageable);

    Page<Notification> searchNotification(String notificationTitle,String notificationContent,String categoryName, String facultyName, String departCenterName,Boolean checkImportant,Date startedDate,Date endedDate,Pageable pageable);

}
