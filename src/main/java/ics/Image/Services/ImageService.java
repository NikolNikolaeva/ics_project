package ics.Image.Services;

import ics.Image.Classes.Image;
import ics.Image.Classes.Tag;
import ics.Image.Classes.TopActiveUserDTO;
import ics.Image.Classes.TopTagDTO;
import ics.Image.Repositories.CommentRepository;
import ics.Image.Repositories.ImageRepository;
import ics.Image.Repositories.TagRepository;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;

@Service
public class ImageService {

    final private ImageRepository imageRepository;
    final private TagRepository tagRepository;
    private final UserService userService;
    private final CommentRepository commentRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, TagRepository tagRepository, UserService userService, CommentRepository commentRepository) {
        this.imageRepository = imageRepository;
        this.tagRepository = tagRepository;
        this.userService = userService;
        this.commentRepository = commentRepository;
    }

    public List<Image> getImages() {
        return imageRepository.findAll();
    }

    public boolean existImage(String url)
    {
        //check if the image already exists in our database
        Optional<Image> imagesByUrl = imageRepository.findImageExistByUrl(url);
        if (imagesByUrl.isPresent()) {
            return true;
        }
        return false;
    }

    //function return true or false depends on if it is saved as new
    public Image addNewImage(Image image,String userId) {

        String imageUrl = image.getUrl();
        URL url;
        try {
            url = new URL(imageUrl);
            URLConnection connection = url.openConnection();

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
        image.setUser(userService.getUserById(Long.parseLong(userId)));
        imageRepository.save(image);
        return image;
    }

    public Image updateImage(Image image) {
        Image updateImg=imageRepository.findImageById(image.getId());

        if(image==null) {
            return null;
        }
        if (image.getComments().size() != updateImg.getComments().size()) {
            updateImg.setComments(image.getComments());
            commentRepository.saveAll(updateImg.getComments());
        }
        if(image.getLikes() != updateImg.getLikes()) {
            updateImg.setLikes(image.getLikes());
        }
        if(image.getDislikes() != updateImg.getDislikes()) {
            updateImg.setDislikes(image.getDislikes());
        }

        if ((image.getUsersRated() != null && !image.getUsersRated().isEmpty()) &&
                (updateImg.getUsersRated() != null && !updateImg.getUsersRated().isEmpty())) {
            if (image.getUsersRated().length() != updateImg.getUsersRated().length()) {
                updateImg.setUsersRated(image.getUsersRated());
            }
        } else if (image.getUsersRated() != null && !image.getUsersRated().isEmpty()) {
            updateImg.setUsersRated(image.getUsersRated());
        }
        imageRepository.save(updateImg);
        return updateImg;
    }

    public void saveTagsInDatabase(List<Tag> tagsToSave)
    {
        for (int i = 0; i < tagsToSave.size(); i++) {
            //Optional<Tag> tagExist=tagRepository.findTagByNameAndConfidence(tagsToSave.get(i).getName(),tagsToSave.get(i).getConfidence());
            //if(!tagExist.isPresent())
            {
                tagRepository.saveAndFlush(tagsToSave.get(i));
            }
        }
    }

    public List<Image> listOfImages(List<String> tags) {
        if (tags!=null) {
            return imageRepository.findImagesByTags(tags);
        }
            return imageRepository.findAllImages();
    }

    public List<Image> listOfImagesByUserId(String userId) {
            return imageRepository.findImagesByUserId(Long.parseLong(userId));
    }

    public Image getImageId(String url) {
            return imageRepository.findImageByUrl(url);
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

    public List<Image> getAllImagesWithDetails(List<String> tagsList){

        return imageRepository.findImagesByTags(tagsList);
    }

    @SneakyThrows
    public List<Image> getImagesWithSpecificTag(String tagName){

        Optional<Tag> tagByName=tagRepository.findTagByName(tagName);
        if(!tagByName.isPresent()){
            //throw new Exception("no images with this tag");
        }

        //???

        return null;
    }

    public Image getImageByUrl(String url)
    {
        return imageRepository.findImageByUrl(url);
    }

    public Image getImageById(Long id) {
        Optional<Image> imageExist=imageRepository.findImageExistsById(id);
        if(!imageExist.isPresent()){
            try {
                throw new ChangeSetPersister.NotFoundException();
            } catch (ChangeSetPersister.NotFoundException e) {
                throw new RuntimeException(e);
            }
        }
            return imageRepository.findImageById(id);
    }

    public List<Image> getTop5MostLikedImages() {
        return imageRepository.findMostLikedImages();
    }

    public List<TopTagDTO> getTop5MostUsedTags() {
        return imageRepository.findTop5Tags();
    }

    public List<TopActiveUserDTO> findTop5UsersWithMostUploads() {
        return imageRepository.findTop5UsersWithMostUploads();
    }
}