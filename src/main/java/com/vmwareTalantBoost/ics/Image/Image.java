package com.vmwareTalantBoost.ics.Image;

import jakarta.persistence.*;

import java.sql.Time;
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
    private String url;
    private Time uploadTime;
    private String service;
    private float height;
    private float width;
    //private List<Tag> tagList;

    public Image() {
    }

    public Image(int id, String url, Time uploadTime, String service, float height, float width, List<Tag> tagList) {
        this.id = id;
        this.url = url;
        this.uploadTime = uploadTime;
        this.service = service;
        this.height = height;
        this.width = width;
        //this.tagList = tagList;
    }

    public Image(int id, String url, Time uploadTime, String service, float height, float width, List<Tag> tags, List<Tag> tagList) {
        this.id = id;
        this.url = url;
        this.uploadTime = uploadTime;
        this.service = service;
        this.height = height;
        this.width = width;
        //this.tagList = tagList;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Time getUploadTime() {
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

   // public List<Tag> getTags() {
   //     return tagList;
   // }
//
    public void setId(long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUploadTime(Time uploadTime) {
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

  //  public void setTags(List<Tag> tags) {
  //      this.tagList = tags;
  //  }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", uploadTime=" + uploadTime +
                ", service='" + service + '\'' +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}
