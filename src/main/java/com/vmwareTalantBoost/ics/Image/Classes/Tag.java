package com.vmwareTalantBoost.ics.Image.Classes;

import jakarta.persistence.*;
import java.util.*;

import java.util.List;

@Entity
@Table
public class Tag {

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
    private String name;
    private int confidence;

    @ManyToMany(mappedBy = "tags")
    Set<Image> images;

    public Tag(){
    }

    public Tag(String name, int confidence, Set<Image> images) {
        this.name = name;
        this.confidence = confidence;
        this.images = images;
    }

    public Tag(String name, int confidence)
    {
        this.name = name;
        this.confidence = confidence;
    }

    public Tag(long id, String name, int confidence) {
        this.id = id;
        this.name = name;
        this.confidence = confidence;
    }

    public String getName() { return name; }
    public int getConfidence() { return confidence; }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                ", confidence=" + confidence +
                '}';
    }
}
