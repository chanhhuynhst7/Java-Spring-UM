package com.project.trainingteam.controller.notification;

import com.project.trainingteam.dto.notification.NotificationDto;
import com.project.trainingteam.entities.notification.Notification;
import com.project.trainingteam.service.inf.notification.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/notification")
public class NotificationController {

    private NotificationService notificationService;

    @PostMapping("/create")
    public ResponseEntity<NotificationDto> createdNotification(@RequestBody Notification notification)throws Exception{
        try{
            NotificationDto result = notificationService.createdNotification(notification);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    };

    @PostMapping("/seen/{notificationId}")
    public ResponseEntity<String> seenByUser(@PathVariable("notificationId")Long notificationId)throws Exception{
        try{
            String result = notificationService.seenByUser(notificationId);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NotificationDto> updatedNotification(@PathVariable("id") Long id ,@RequestBody Notification notification)throws Exception{
        try{
            notification.setId(id);
            NotificationDto result = notificationService.updatedNotification(notification);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Page<NotificationDto>> getAllNotification(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                    @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                    @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                                    @RequestParam(name = "content", defaultValue = "id") String content) throws Exception{
        try {
            Page<NotificationDto> result = notificationService.getAllNotification(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedNotification(@PathVariable("id")Long id)throws Exception{
        try{
            String result = notificationService.deletedNotification(id);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
