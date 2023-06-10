package com.project.trainingteam.repo.impl;

import com.project.trainingteam.entities.notification.Notification;
import com.project.trainingteam.repo.inf.notification.NotificationCustomRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TemporalType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Repository
public class NotificationCustomRepoImpl implements NotificationCustomRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Notification> searchNotificationByFacultyName(String notificationTitle, String notificationContent, String categoryName, String facultyName, Date startedDate, Date endedDate, Pageable pageable) {
        String sqlQuery = "SELECT * " +
                "FROM um_notification n " +
                "WHERE (n.notification_title LIKE :notificationTitle) " +
                "AND (n.notification_content LIKE :notificationContent) " +
                "AND n.category_name = :categoryName " +
                "AND n.faculty_name = :facultyName " +
                "AND DATE_TRUNC('month', n.created_date) BETWEEN DATE(:startedDate) AND DATE(:endedDate)";

        Query query = entityManager.createNativeQuery(sqlQuery, Notification.class);
        query.setParameter("notificationTitle", "%" + notificationTitle);
        query.setParameter("notificationContent", "%" + notificationContent);
        query.setParameter("categoryName", categoryName);
        query.setParameter("facultyName", facultyName);
        query.setParameter("startedDate", startedDate, TemporalType.DATE);
        query.setParameter("endedDate", endedDate, TemporalType.DATE);

        List<Notification> resultList = query.getResultList();
        int totalResults = resultList.size();

        int offset = (int) pageable.getOffset();
        int pageSize = pageable.getPageSize();
        int toIndex = Math.min(offset + pageSize, totalResults);

        List<Notification> pageResults = resultList.subList(offset, toIndex);

        return new PageImpl<>(pageResults, pageable, totalResults);
    }

    public Page<Notification> searchNotificationByDepartCenterName(String notificationTitle, String notificationContent, String categoryName, String departCenterName, Date startedDate, Date endedDate, Pageable pageable) {
        String sqlQuery = "SELECT * " +
                "FROM um_notification n " +
                "WHERE (n.notification_title LIKE :notificationTitle) " +
                "AND (n.notification_content LIKE :notificationContent) " +
                "AND n.category_name = :categoryName " +
                "AND n.depart_center_name = :departCenterName " +
                "AND DATE_TRUNC('month', n.created_date) BETWEEN DATE(:startedDate) AND DATE(:endedDate)";

        Query query = entityManager.createNativeQuery(sqlQuery, Notification.class);
        query.setParameter("notificationTitle", "%" + notificationTitle);
        query.setParameter("notificationContent", "%" + notificationContent);
        query.setParameter("categoryName", categoryName);
        query.setParameter("departCenterName", departCenterName);
        query.setParameter("startedDate", startedDate, TemporalType.DATE);
        query.setParameter("endedDate", endedDate, TemporalType.DATE);

        List<Notification> resultList = query.getResultList();
        int totalResults = resultList.size();

        int offset = (int) pageable.getOffset();
        int pageSize = pageable.getPageSize();
        int toIndex = Math.min(offset + pageSize, totalResults);

        List<Notification> pageResults = resultList.subList(offset, toIndex);

        return new PageImpl<>(pageResults, pageable, totalResults);
    }

    @Override
    public Page<Notification> searchNotification(String notificationTitle, String notificationContent, String categoryName, String facultyName, String departCenterName, Boolean checkImportant, Date startedDate, Date endedDate, Pageable pageable) {

       return null;
    }
}
