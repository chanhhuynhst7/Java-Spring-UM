package com.project.trainingteam.service.impl.notification;

import com.project.trainingteam.dto.notification.NotificationDto;
import com.project.trainingteam.entities.notification.Notification;
import com.project.trainingteam.repo.inf.notification.NotificationRepo;
import com.project.trainingteam.service.inf.notification.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NotificationRepo notificationRepo;

    @Override
    public NotificationDto createdNotification(Notification notification) throws Exception {
        try{
            Notification savedNotification = notificationRepo.save(notification);
            return modelMapper.map(savedNotification,NotificationDto.class);
        }catch (Exception e){
            throw new Exception("Không thể tạo mới");
        }
    };

    @Override
    public NotificationDto updatedNotification(Notification notification) throws Exception {
        try{
            Notification findNotification = notificationRepo.findById(notification.getId()).get();
            findNotification.setNotificationTitle(notification.getNotificationTitle());
            findNotification.setNotificationContent(notification.getNotificationContent());
            findNotification.setFacultyName(notification.getFacultyName());
            findNotification.setDepartCenterName(notification.getDepartCenterName());
            findNotification.setCategoryName(notification.getCategoryName());
            Notification savedNotification = notificationRepo.save(findNotification);
            return modelMapper.map(savedNotification,NotificationDto.class);
        }catch (Exception e){
            throw new Exception("Không thể update");
        }
    }

    @Override
    public String seenByUser(Long notificationId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Notification notification = notificationRepo.findNotificationById(notificationId);
        if (notification != null) {
            Set<String> seenByUser = notification.getSeenByUser();
            if (seenByUser == null || !seenByUser.contains(currentName)) {
                seenByUser = new HashSet<>();
                seenByUser.add(currentName);
                notification.setSeenByUser(seenByUser);
                notificationRepo.save(notification);
                return currentName + " vừa xem";
            } else {
                return currentName + " đã xem trước đó";
            }
        } else {
            throw new NoSuchElementException("Không tìm thấy thông báo với id: " + notificationId);
        }
    }

    @Override
    public Page<NotificationDto> getAllNotification(Pageable pageable) throws Exception {

        try{
            Page<Notification> notificationPage = notificationRepo.findAll(pageable);
            List<Notification> notificationList = notificationPage.getContent();
            List<NotificationDto> notificationDtoList = notificationList.stream().map(result ->modelMapper.map(result,NotificationDto.class)).collect(Collectors.toList());
            if( notificationDtoList != null){
                return new PageImpl<>(notificationDtoList,pageable,notificationPage.getTotalElements());
            }else{
                throw new Exception("Không tìm thấy Notification");
            }
        }catch (Exception e){
            throw new Exception("Không thể tìm Notification");
        }

    }

    @Override
    public String deletedNotification(Long id) throws Exception {
        try{
            notificationRepo.deleteById(id);
            return "DELETE Thành Công";
        }catch (Exception e){
            throw new Exception("không thể xóa");
        }
    }
}
