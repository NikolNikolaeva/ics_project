package com.vmwareTalantBoost.ics.Image.Classes;

import jakarta.persistence.ElementCollection;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToStdout;

import java.util.List;
import java.util.Set;

public class ImageTagsObject {

    private Image image;
    private Set<Tag> tags;

    public ImageTagsObject(){}

    public ImageTagsObject(Image image,  Set<Tag>  tags) {
        this.image = image;
        this.tags = tags;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setTags( Set<Tag>  tags) {
        this.tags = tags;
    }

    public  Set<Tag>  getTags() {
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
