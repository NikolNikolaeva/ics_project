package ics.Image.Controllers;

import ics.Image.Classes.Image;
import ics.Image.Classes.ImageRequestDTO;
import ics.Image.Classes.Tag;
import ics.Image.Services.ImageService;
import ics.Image.Services.ImaggaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(path = "/id")
    public Image getImageById(@RequestParam long id) {
        return imageService.getImageById(id);
    }

    @PostMapping
    public Image registerNewImage(@RequestBody ImageRequestDTO data){

        String imgUrl=data.getImgUrl();
        String token=data.getToken();

        //if image url already exist in our database
         if(imageService.existImage(imgUrl))
         {
             return imageService.getImageByUrl(imgUrl);
         }

        Image image=new Image();
        image.setService("Imagga");
        image.setUrl(imgUrl);
        List<Tag> tags=new ArrayList<Tag>();
        tags = imaggaService.getTagsFromImage(imgUrl);

        image.setTags(tags);
        imageService.saveTagsInDatabase(tags);

        return imageService.addNewImage(image, token);
    }

    @DeleteMapping(path="/delete/{id}")
    public void deleteImage(@PathVariable("id") Long imageId){
         imageService.deleteImage(imageId);
    }

}
