package com.vmwareTalantBoost.ics.Image.Classes;

import jakarta.persistence.ElementCollection;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToStdout;

import java.util.List;

public class ImageTagsObject {

    private Image image;
    private List<Tag> tags;

    public ImageTagsObject(){}

    public ImageTagsObject(Image image, List<Tag> tags) {
        this.image = image;
        this.tags = tags;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "ImageTagsObject{" +
                "image=" + image +
                ", tags=" + tags +
                '}';
    }
}
