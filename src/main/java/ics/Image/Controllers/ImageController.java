package ics.Image.Controllers;

import ics.Image.Classes.*;
import ics.Image.Services.ImageService;
import ics.Image.Services.ImaggaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "images")
public class ImageController {

     private final ImageService imageService;
     private final ImaggaService imaggaService;

    @Autowired
    public ImageController(ImageService imageService, ImaggaService imaggaService) {
        this.imageService = imageService;
         this.imaggaService = imaggaService;
    }

    @GetMapping
    public List<Image> getListImages(@RequestParam(required = false) List<String> tags) {
        return imageService.listOfImages(tags);
    }

    @GetMapping(path="/user")
    public List<Image> getListImagesById(@RequestParam("userId") String userId) {
        return imageService.listOfImagesByUserId(userId);
    }

    @GetMapping(path = "/id")
    public Image getImageById(@RequestParam long id) {
        return imageService.getImageById(id);
    }

    @PostMapping
    public Image registerNewImage(@RequestBody ImageRequestDTO data){

        String imgUrl=data.getImgUrl();
        String token=data.getToken();
        Boolean privateImg=data.isPrivateImg();

        //if image url already exist in our database
         if(imageService.existImage(imgUrl))
         {
             return imageService.getImageByUrl(imgUrl);
         }

        Image image=new Image();
        image.setService("Imagga");
        image.setUrl(imgUrl);
        image.setPrivateImg(privateImg);
        List<Tag> tags=new ArrayList<Tag>();
        tags = imaggaService.getTagsFromImage(imgUrl);

        image.setTags(tags);
        imageService.saveTagsInDatabase(tags);

        return imageService.addNewImage(image, token);
    }

    @PutMapping(path = "/update")
    public Image updateImage(@RequestBody Image image){
       return imageService.updateImage(image);
    }

    @DeleteMapping(path="/delete/{id}")
    public void deleteImage(@PathVariable("id") Long imageId){
         imageService.deleteImage(imageId);
    }

    @GetMapping(path = "/top-liked")
    public List<Image> getTop5MostLikedImages() {
        return imageService.getTop5MostLikedImages();
    }

    @GetMapping(path = "/top-tags")
    public  List<TopTagDTO> getTop5MostUsedTags() {
        return imageService.getTop5MostUsedTags();
    }

    @GetMapping(path = "/top-users")
    public  List<TopActiveUserDTO> findTop5UsersWithMostUploads() {
        return imageService.findTop5UsersWithMostUploads();
    }
}
