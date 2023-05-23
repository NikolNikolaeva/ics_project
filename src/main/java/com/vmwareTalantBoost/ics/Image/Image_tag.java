package com.vmwareTalantBoost.ics.Image;

import jakarta.persistence.*;

import java.util.*;


@Entity
@Table
public class Image_tag {

    @Id
    @SequenceGenerator(
            name = "image_sequence",
            sequenceName = "image_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "image_sequence"
    )
    private long id;
    private long id_image;
    private long[] tags;


    public Image_tag() {
    }

    public Image_tag(long idImage, long[] tags) {
        id_image = idImage;
        this.tags = tags;
    }

    public Image_tag(long id, long id_image, long[] tags) {
        this.id = id;
        this.id_image = id_image;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long[] getTags() {
        return tags;
    }

    public void setTags(long[] tags) {
        this.tags = tags;
    }

    public long getId_image() {
        return id_image;
    }

    public void setId_image(long id_image) {
        this.id_image = id_image;
    }

    @Override
    public String toString() {
        return "Image_tag{" +
                "id=" + id +
                ", id_image=" + id_image +
                ", tags=" + tags +
                '}';
    }

}