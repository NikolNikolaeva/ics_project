package com.vmwareTalantBoost.ics.Image.Classes;

import jakarta.persistence.*;
import java.time.format.DateTimeFormatter;

import java.sql.Time;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

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

    private Date uploadTime;
    private String service;
    private float height;
    private float width;


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
