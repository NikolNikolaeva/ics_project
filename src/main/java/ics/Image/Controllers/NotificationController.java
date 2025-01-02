package ics.Image.Controllers;


import ics.Image.Classes.Image;
import ics.Image.Classes.ImageRequestDTO;
import ics.Image.Classes.Notification;
import ics.Image.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping(path="/userId")
    public List<Notification> getListImages(@RequestParam("userId") Long userId) {
       // notificationService.deleteOldNotifications(userId);
        return notificationService.getLatest7Notifications(userId);
    }


    @PostMapping
    public Notification registerNewNotification(@RequestBody Notification notification) {
        return notificationService.save(notification);
    }

}


