package com.vmwareTalantBoost.ics.Image.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmwareTalantBoost.ics.Image.Classes.Image;
import com.vmwareTalantBoost.ics.Image.Classes.ImageTagsObject;
import com.vmwareTalantBoost.ics.Image.Classes.Tag;
import com.vmwareTalantBoost.ics.Image.Repositories.ImageRepository;
import com.vmwareTalantBoost.ics.Image.Repositories.TagRepository;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;

import static org.apache.logging.log4j.util.Strings.join;

@Service
public class ImageService {

    final private ImageRepository imageRepository;
    final private TagRepository tagRepository;
   // final private Image_tagRepository imageTagRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, TagRepository tagRepository ) {
        this.imageRepository = imageRepository;
        this.tagRepository = tagRepository;
    }

    public List<Image> getImages() {
        return imageRepository.findAll();
    }

    public boolean existImage(String url)
    {
        //check if the image already exists in our database
        Optional<Image> imagesByUrl = imageRepository.findImagesByUrl(url);
        if (imagesByUrl.isPresent()) {
            return true;
        }
        return false;
    }

    //function return true or false depends on if it is saved as new
    public Image addNewImage(Image image) {

        String imageUrl = image.getUrl();
        URL url;
        try {
            url = new URL(imageUrl);
            URLConnection connection = url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //check if the url is an image and get its details
        BufferedImage imageBuff;
        try {
            imageBuff = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e + "url is not an image");
        }
        int height = imageBuff.getHeight();
        int width = imageBuff.getWidth();

        //save the image in database
        image.setHeight(height);
        image.setWidth(width);
        image.setUrl(imageUrl);
        imageRepository.save(image);
        return image;
    }

    public List<Image> listOfImages(List<String> tags) {
        if (tags != null) {
            return imageRepository.findImagesByTags(tags);
        }
            return imageRepository.findAllImages();
    }

    public Long getImageId(String url) {
            return imageRepository.findImageId(url);
    }

    public void deleteImage(Long imageId) {
        boolean exists = imageRepository.existsById(imageId);
        if (!exists) {
            throw new IllegalStateException(
                    "Image with id " + imageId + " does not exists"
            );
        }
        imageRepository.deleteById(imageId);
    }

    public Set<Tag> getTagList(String jsonString) {

        Set<Tag>  tags = new HashSet<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            JsonNode tagsNode = jsonNode.get("result").get("tags");
            if (tagsNode.isArray()) {
                for (JsonNode tagNode : tagsNode) {
                    JsonNode tag = tagNode.get("tag");

                    Tag tagImage = new Tag();
                    String tagName = tag.get("en").asText();
                    int tagConfidence = tagNode.get("confidence").asInt();
                    tagImage.setName(tagName);
                    if (tagConfidence > 30) {
                        tagImage.setConfidence(tagConfidence);
                        tags.add(tagImage);
                    }
                }
            }

            return tags;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tags;
    }

    //json object with image url and its tags
    public ImageTagsObject getObjectForImageDetails(Image image,  Set<Tag>  tags) {
        ImageTagsObject imageTagsObject=new ImageTagsObject();

        Set<Tag>  tagsArray=tags;

        //if image already exists
//       if(tags==null) {
//
//       }

        imageTagsObject.setTags(tagsArray);
        imageTagsObject.setImage(image);

        return imageTagsObject;
    }

    public List<ImageTagsObject> getAllImagesWithDetails(){

        List<Image> images=imageRepository.findAllImages();
        List<ImageTagsObject> array=new ArrayList<ImageTagsObject>();

        for (int i = 0; i < images.size(); i++) {
            array.add(getObjectForImageDetails(images.get(i),null));
        }
        return array;
    }

    @SneakyThrows
    public List<Image> getImagesWithSpecificTag(String tagName){

        Optional<Tag> tagByName=tagRepository.findTagByName(tagName);
        if(!tagByName.isPresent()){
            throw new Exception("no images with this tag");
        }

        //???

        return null;
    }

}




