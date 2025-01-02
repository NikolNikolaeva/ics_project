package ics.Image.Repositories;

import ics.Image.Classes.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {


    // Fetch the IDs of the top 7 latest notifications
    @Query("SELECT n FROM Notification n WHERE n.whoseNotification = :userId ORDER BY n.date DESC limit 7")
    List<Notification> findTop7IdsByWhoseNotificationOrderByDateDesc(Long userId);

    @Modifying
    @Query(value = "DELETE FROM notification " +
            "WHERE whose_notification = :userId " +
            "AND id NOT IN (SELECT id FROM notification WHERE whose_notification = :userId ORDER BY date DESC LIMIT 7)",
            nativeQuery = true)
    void deleteByWhoseNotificationAndIdNotIn(Long userId);
}
