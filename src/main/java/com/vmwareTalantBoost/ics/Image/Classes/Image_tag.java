//package com.vmwareTalantBoost.ics.Image.Classes;
//
//import jakarta.persistence.*;
//import org.json.simple.JSONObject;
//
//import java.io.Serializable;
//import java.util.*;
//
//
//@Embeddable
//public class Image_tag implements Serializable {
//    @Column(name = "image_id")
//    Long id_image;
//
//    @Column(name = "tag_id")
//     Long id_tag;
//
//    public Image_tag() {
//    }
//
//    public Long getId_image() {
//        return id_image;
//    }
//
//    public void setId_image(Long id_image) {
//        this.id_image = id_image;
//    }
//
//    public Long getId_tag() {
//        return id_tag;
//    }
//
//    public void setId_tag(Long id_tag) {
//        this.id_tag = id_tag;
//    }
//
//    public Image_tag(Long id_image, Long id_tag) {
//        this.id_image = id_image;
//        this.id_tag = id_tag;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Image_tag imageTag)) return false;
//        return Objects.equals(getId_image(), imageTag.getId_image()) && Objects.equals(getId_tag(), imageTag.getId_tag());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId_image(), getId_tag());
//    }
//}