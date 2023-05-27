package com.vmwareTalantBoost.ics.Image.Classes;

import jakarta.persistence.*;

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

    public Tag(){
    }
    public Tag(String name,int confidence)
    {
        this.name = name;
        this.confidence = confidence;
    }

    public String getName() { return name; }
    public int getConfidence() { return confidence; }

    public void setName(String name) {
        this.name = name;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tag(long id, String name, int confidence) {
        this.id = id;
        this.name = name;
        this.confidence = confidence;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                ", confidence=" + confidence +
                '}';
    }
}
