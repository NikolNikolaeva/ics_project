package ics.Image.Classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Image {

    @Id
    @SequenceGenerator(
            name="image_sequence",
            sequenceName = "image_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "image_sequence"
    )
    private long id;

    @Column(name="url",columnDefinition="TEXT", length = 4096)
    private String url;
    @Column(name="upload_time")
    private Date uploadTime;
    @Column(name="service")
    private String service;
    @Column(name="height")
    private float height;
    @Column(name="width")
    private float width;
    @Column(name="privateImg")
    private boolean privateImg;
    @Column(name="likes")
    private long likes;
    @Column(name="dislikes")
    private long dislikes;
    @Column(name="usersRated")
    private String usersRated;

    @ManyToMany
    @JoinTable(
            name = "tag_image",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    List<Tag> tags;

    @ManyToOne
    User user;

    @OneToMany
    @JoinTable(
            name = "image_comments",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    List<Comment> comments;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Image(String url, Date uploadTime, String service, float height, float width, List<Tag> tags) {
        this.url = url;
        this.uploadTime = uploadTime;
        this.service = service;
        this.height = height;
        this.width = width;
        this.tags = tags;
    }

    public Image(long id, String url, Date uploadTime, String service, float height, float width) {
        this.id = id;
        this.url = url;
        this.uploadTime = uploadTime;
        this.service = service;
        this.height = height;
        this.width = width;
    }

    public Image( String url, String service, float height, float width) {
        this.url = url;
        this.uploadTime = new Date();
        this.service = service;
        this.height = height;
        this.width = width;
    }

    public Image(String url) {
        this.url = url;
    }

    public Image() {
        uploadTime=new Date();
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public String getService() {
        return service;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public User getUser() {
        return user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPrivateImg(boolean privateImg) {
        this.privateImg = privateImg;
    }

    public boolean isPrivateImg() {
        return privateImg;
    }

    public long getLikes() {
        return likes;
    }
    public void setLikes(long likes) {
        this.likes = likes;
    }
    public long getDislikes() {
        return dislikes;
    }
    public void setDislikes(long dislikes) {
        this.dislikes = dislikes;
    }
    public String getUsersRated() { return usersRated; }
    public void setUsersRated(String usersRated) { this.usersRated = usersRated; }

    public List<Comment> getComments() {
        return comments;
    }
   public void setComments(List<Comment> comments) {
        this.comments = comments;
   }
    public Image(String url, float height, float width) {
        this.url = url;
        this.height = height;
        this.width = width;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", uploadTime=" + uploadTime +
                ", service='" + service + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", tags="+ tags +
                ", user=" + user.getUsername() +
                ", privateImg=" + privateImg +
                '}';
    }
}
