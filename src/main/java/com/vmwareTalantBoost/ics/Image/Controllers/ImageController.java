package com.vmwareTalantBoost.ics.Image.Controllers;

import com.vmwareTalantBoost.ics.Image.Classes.Image;
import com.vmwareTalantBoost.ics.Image.Classes.ImageTagsObject;
import com.vmwareTalantBoost.ics.Image.Classes.Tag;
import com.vmwareTalantBoost.ics.Image.Services.ImageService;
import com.vmwareTalantBoost.ics.Image.Services.ImaggaService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Tag> imageTagsListFromImagga(String image_url) {
        return imaggaService.getTagsFromImage(image_url);
    }

    @PostMapping
    public ImageTagsObject registerNewImage(@RequestBody Image image){

        //if image url already exist in our database
         if(imageService.existImage(image.getUrl()))
         {
             return imageService.getObjectForImageDetails(image,null);
         }

         if(image.getService()==null) {
             image.setService("Imagga");
         }

        imageService.addNewImage(image);
         Long imageId= imageService.getImageId(image.getUrl());

         //from getMapping
         List<Tag> tags=imageTagsListFromImagga(image.getUrl());

       //if(!tags.isEmpty()) {
       //    List<Long> tagsIds=imageService.addNewTags(tags);
       // }

        return imageService.getObjectForImageDetails(image,tags);
    }

    @DeleteMapping(path="{id}")
    public void deleteImage(@PathVariable("id") Long imageId){
         imageService.deleteImage(imageId);
    }

    public List<ImageTagsObject> getAllImages()
    {
        return imageService.getAllImagesWithDetails();
    }

    public JSONArray getAllImagesWithSpecificTag(){
         return null;
    }
}
