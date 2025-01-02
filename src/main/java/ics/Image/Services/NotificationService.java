package ics.Image.Services;

import ics.Image.Classes.Notification;
import ics.Image.Classes.User;
import ics.Image.Repositories.NotificationRepository;
import ics.Image.Repositories.UserRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    final private NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public List<Notification> getLatest7Notifications(Long userId) {
        return notificationRepository.findTop7IdsByWhoseNotificationOrderByDateDesc(userId);
    }

    @Async
    @Scheduled(fixedRate = 86400000) // 86400000 ms = 24 hours
    @Transactional
    public void performTask() {
        System.out.println("Task executed at: " + java.time.LocalDateTime.now());
        // Add your logic here
        for (User user : userRepository.findAll()) {
            notificationRepository.deleteByWhoseNotificationAndIdNotIn(user.getId());
        }
    }

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }
}
