package com.vmwareTalantBoost.ics.Image.Controllers;

import com.vmwareTalantBoost.ics.Image.Classes.Image;
import com.vmwareTalantBoost.ics.Image.Classes.Tag;
import com.vmwareTalantBoost.ics.Image.Services.ImageService;
import com.vmwareTalantBoost.ics.Image.Services.ImaggaService;
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
    public Image registerNewImage(@RequestBody String url){

        //if image url already exist in our database
         if(imageService.existImage(url))
         {
             return imageService.getImageByUrl(url);
         }

        Image image=new Image();
        image.setService("Imagga");
        image.setUrl(url);
        List<Tag> tags=new ArrayList<Tag>();
        tags = imaggaService.getTagsFromImage(url);

        image.setTags(tags);
        imageService.saveTagsInDatabase(tags);

        return imageService.addNewImage(image);
    }

    @DeleteMapping(path="/delete/{id}")
    public void deleteImage(@PathVariable("id") Long imageId){
         imageService.deleteImage(imageId);
    }

}
