package com.vmwareTalantBoost.ics.Image.Classes;

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

    @ElementCollection
    private List<Long> tags;


    public Image_tag() {
    }

    public Image_tag(long idImage, List<Long> tags) {
        id_image = idImage;
        this.tags = tags;
    }

    public Image_tag(long id, long id_image, List<Long> tags) {
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

    public List<Long> getTags() {
        return tags;
    }

    public void setTags(List<Long> tags) {
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