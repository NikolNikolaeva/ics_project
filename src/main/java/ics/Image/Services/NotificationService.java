package ics.Image.Services;

import ics.Image.Classes.Notification;
import ics.Image.Repositories.NotificationRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    final private NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getLatest17Notifications(Long userId) {
        return notificationRepository.findTop7ByWhoseNotificationOrderByDateDesc(userId);
    }

    // Delete all notifications except the latest 10
    public void deleteOldNotifications(Long userId) {
        notificationRepository.deleteOldNotifications(userId);
    }

    // Combined operation
    public List<Notification> getLatestAndCleanup(Long userId) {
        List<Notification> latestNotifications = getLatest17Notifications(userId);

        deleteOldNotifications(userId);

        return latestNotifications;
    }

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }
}
