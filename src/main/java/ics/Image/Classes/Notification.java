package ics.Image.Classes;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Notification {

    @Id
    @SequenceGenerator(
            name="notification_sequence",
            sequenceName = "notification_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_sequence"
    )
    private long id;

    @Column(name="whoseNotification")
    private long whoseNotification;
    @Column(name="whoseAction")
    private String whoseAction;
    @Column(name="action")
    private String action;
    @Column(name="date")
    private Date date;
    @Column(name="imgId")
    private long imgId;

    public Notification() {
    }

    public Notification(long whoseNotification, String whoseAction, String action, Date date, long imgId) {
        this.whoseNotification = whoseNotification;
        this.whoseAction = whoseAction;
        this.action = action;
        this.date = date;
        this.imgId = imgId;
    }

    public long getId() {
        return id;
    }
    private void setId(long id) {
        this.id = id;
    }
    public long getWhoseNotification() {
        return whoseNotification;
    }
    public void setWhoseNotification(long whoseNotification) {
        this.whoseNotification = whoseNotification;
    }
    public String getWhoseAction() {
        return whoseAction;
    }
    public void setWhoseAction(String whoseAction) {
        this.whoseAction = whoseAction;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public long getImgId() {
        return imgId;
    }
    public void setImgId(long imgId) {
        this.imgId = imgId;
    }
}
