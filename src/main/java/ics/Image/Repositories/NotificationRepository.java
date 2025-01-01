package ics.Image.Repositories;

import ics.Image.Classes.Image;
import ics.Image.Classes.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {


    @Query("SELECT n FROM Notification n WHERE n.whoseNotification = :userId ORDER BY n.date DESC LIMIT 7")
    List<Notification> findTop7ByWhoseNotificationOrderByDateDesc(Long userId);

    @Modifying
    @Query("DELETE FROM Notification n WHERE n.whoseNotification = :userId AND n.id NOT IN " +
            "(SELECT n.id FROM Notification n WHERE n.whoseNotification = :userId ORDER BY n.date DESC LIMIT 10)")
    void deleteOldNotifications(Long userId);

}
