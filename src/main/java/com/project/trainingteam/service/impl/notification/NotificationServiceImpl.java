package com.project.trainingteam.service.impl.notification;

import com.project.trainingteam.dto.notification.DashBoardUnSeenNotificationDto;
import com.project.trainingteam.dto.notification.NotificationDto;
import com.project.trainingteam.dto.notification.PageUnSeenNotificationDto;
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
    public NotificationDto getNotificationById(Notification notification) throws Exception {
        try{
            Notification findNotification = notificationRepo.findById(notification.getId()).get();
            if(findNotification != null ){
                return modelMapper.map(findNotification,NotificationDto.class);
            }else{
                throw new Exception("Không tìm thấy Notification");
            }
        }catch (Exception e){
            throw new Exception("không thể tìm Notification");
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
    public List<NotificationDto> getNewNotificationList() throws Exception {
        try{
            List<Notification> notification = notificationRepo.findNewNotification();
            List<NotificationDto> notificationDtoList = notification.stream().map(result ->modelMapper.map(result,NotificationDto.class)).collect(Collectors.toList());
            return notificationDtoList;
        }catch (Exception e){
            throw new Exception("Không tìm thấy Notification");
        }
    };

    @Override
    public List<NotificationDto> getImportantNotificationList() throws Exception {
        try{
            List<Notification> notification = notificationRepo.findImportantNotification();
            List<NotificationDto> notificationDtoList = notification.stream().map(result ->modelMapper.map(result,NotificationDto.class)).collect(Collectors.toList());
            return notificationDtoList;
        }catch (Exception e){
            throw new Exception("Không tìm thấy Notification");
        }
    }

    @Override
    public List<DashBoardUnSeenNotificationDto> getUnseenCountNotificationByCategoryName() throws Exception {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentName = authentication.getName();
            List<DashBoardUnSeenNotificationDto> notificationDtoList = new ArrayList<>();
            List<Object[]> notificationList = notificationRepo.findUnseenNewsCountByCategory(currentName);
            for (Object[] objects : notificationList) {
                String categoryName = (String) objects[0]; // Cast or convert to the appropriate type
                Long countNotificationUnSeen = (Long) objects[1]; // Cast or convert to the appropriate type

                DashBoardUnSeenNotificationDto dashBoardUnSeenNotificationDto = new DashBoardUnSeenNotificationDto();
                dashBoardUnSeenNotificationDto.setCategoryName(categoryName);
                dashBoardUnSeenNotificationDto.setCountNotificationUnSeen(countNotificationUnSeen);
                notificationDtoList.add(dashBoardUnSeenNotificationDto);
            }
            return notificationDtoList;
        } catch (Exception e) {
            throw new Exception("Không tìm thấy Notification");
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
    public Page<PageUnSeenNotificationDto> getUnSeenNotificationByCategoryName(String categoryName, Pageable pageable) throws Exception {
        try {
            Page<Object[]> notificationPage = notificationRepo.getUnSeenNotificationByCategoryName(categoryName, pageable);
            List<Object[]> notificationList = notificationPage.getContent();
            List<PageUnSeenNotificationDto> notificationDtoList = new ArrayList<>();
            for (Object[] objects : notificationList) {
                Long id = (Long) objects[0];
                String notificationTitle = (String) objects[1];
                String notificationContent = (String) objects[2];
                String facultyName = (String) objects[3];
                String departCenterName = (String) objects[4];
                Date createdDate = (Date) objects[5];
                Date lastModifiedDate = (Date) objects[6];
                PageUnSeenNotificationDto pageUnSeenNotificationDto = new PageUnSeenNotificationDto();
                pageUnSeenNotificationDto.setId(id);
                pageUnSeenNotificationDto.setNotificationTitle(notificationTitle);
                pageUnSeenNotificationDto.setNotificationContent(notificationContent);
                pageUnSeenNotificationDto.setFacultyName(facultyName);
                pageUnSeenNotificationDto.setDepartCenterName(departCenterName);
                pageUnSeenNotificationDto.setCreatedDate(createdDate);
                pageUnSeenNotificationDto.setLastModifiedDate(lastModifiedDate);
                notificationDtoList.add(pageUnSeenNotificationDto);
            }
            if (notificationDtoList != null) {
                return new PageImpl<>(notificationDtoList, pageable, notificationPage.getTotalElements());
            } else {
                throw new Exception("Không tìm thấy Notification");
            }
        } catch (Exception e) {
            throw new Exception("Không thể tìm Notification");
        }
    }


    @Override
    public Page<NotificationDto> getAllImportantNotification(Pageable pageable) throws Exception {
        try{
            Page<Notification> notificationPage = notificationRepo.getAllImportantNotification(pageable);
            List<Notification> notificationList = notificationPage.getContent();
            List<NotificationDto> notificationDtoList = notificationList.stream().map(result -> modelMapper.map(result,NotificationDto.class)).collect(Collectors.toList());
            if(notificationDtoList != null ){
                return new PageImpl<>(notificationDtoList,pageable,notificationPage.getTotalElements());
            }else{
                throw new Exception("Không tìm thấy Notification");
            }
        }catch (Exception e){
            throw new Exception("Không thể tìm Notification");
        }
    }

    @Override
    public Page<NotificationDto> getAllNewNotification(Pageable pageable) throws Exception {
        try{
            Page<Notification> notificationPage = notificationRepo.getAllNewNotification(pageable);
            List<Notification> notificationList = notificationPage.getContent();
            List<NotificationDto> notificationDtoList = notificationList.stream().map(result -> modelMapper.map(result,NotificationDto.class)).collect(Collectors.toList());
            if(notificationDtoList != null ){
                return new PageImpl<>(notificationDtoList,pageable,notificationPage.getTotalElements());
            }else{
                throw new Exception("Không tìm thấy Notification");
            }
        }catch (Exception e){
            throw new Exception("Không thể tìm Notification");
        }
    }

    @Override
    public Page<NotificationDto> getAllNotificationByFacultyName(String facultyName, Pageable pageable) throws Exception {
        try{
            Page<Notification> notificationPage = notificationRepo.getAllNotificationByFacultyName(facultyName,pageable);
            List<Notification> notificationList = notificationPage.getContent();
            List<NotificationDto> notificationDtoList = notificationList.stream().map(result -> modelMapper.map(result,NotificationDto.class)).collect(Collectors.toList());
            if(notificationDtoList != null ){
                return new PageImpl<>(notificationDtoList,pageable,notificationPage.getTotalElements());
            }else{
                throw new Exception("Không tìm thấy Notification");
            }
        }catch (Exception e){
            throw new Exception("Không thể tìm Notification");
        }
    }

    @Override
    public Page<NotificationDto> getAllNotificationByCategoryName(String categoryName, Pageable pageable) throws Exception {
        try{
            Page<Notification> notificationPage = notificationRepo.getAllNotificationByCategoryName(categoryName,pageable);
            List<Notification> notificationList = notificationPage.getContent();
            List<NotificationDto> notificationDtoList = notificationList.stream().map(result -> modelMapper.map(result,NotificationDto.class)).collect(Collectors.toList());
            if(notificationDtoList != null ){
                return new PageImpl<>(notificationDtoList,pageable,notificationPage.getTotalElements());
            }else{
                throw new Exception("Không tìm thấy Notification");
            }
        }catch (Exception e){
            throw new Exception("Không thể tìm Notification");
        }
    }

    @Override
    public Page<NotificationDto> getAllNotificationByDepartCenterName(String departCenterName, Pageable pageable) throws Exception {
        try{
            Page<Notification> notificationPage = notificationRepo.getAllNotificationByDePartCenterName(departCenterName,pageable);
            List<Notification> notificationList = notificationPage.getContent();
            List<NotificationDto> notificationDtoList = notificationList.stream().map(result -> modelMapper.map(result,NotificationDto.class)).collect(Collectors.toList());
            if(notificationDtoList != null ){
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
