package com.vmwareTalantBoost.ics.Image.Controllers;

import com.vmwareTalantBoost.ics.Image.Classes.Image;
import com.vmwareTalantBoost.ics.Image.Classes.Tag;
import com.vmwareTalantBoost.ics.Image.Services.ImageService;
import com.vmwareTalantBoost.ics.Image.Services.ImaggaService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "images")
public class ImageController {

     private final ImageService imageService;
     private final ImaggaService imaggaService;
    private final TagsController tagController;

    @Autowired
    public ImageController(ImageService imageService, ImaggaService imaggaService, TagsController tagController) {
        this.imageService = imageService;
         this.imaggaService = imaggaService;
        this.tagController = tagController;
    }

    @GetMapping
    public List<Image> getListImages(@RequestParam(required = false) List<String> tags) {
        return imageService.listOfImages(tags);
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
         List<Tag> tags;
        tags = tagController.imageTagsListFromImagga(url);

        image.setTags(tags);
        imageService.saveTagsInDatabase(tags);
         return imageService.addNewImage(image);

         //add tags

    }

    @DeleteMapping(path="{id}")
    public void deleteImage(@PathVariable("id") Long imageId){
         imageService.deleteImage(imageId);
    }

}
