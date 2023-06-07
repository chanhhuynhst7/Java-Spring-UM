package com.project.trainingteam.service.inf.notification;

import com.project.trainingteam.dto.notification.NotificationDto;
import com.project.trainingteam.entities.notification.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

    NotificationDto createdNotification(Notification notification)throws Exception;

    NotificationDto updatedNotification(Notification notification)throws Exception;

    String seenByUser(Long notificationId) throws Exception;

    Page<NotificationDto> getAllNotification(Pageable pageable) throws Exception;

    String deletedNotification(Long id)throws Exception;
}
