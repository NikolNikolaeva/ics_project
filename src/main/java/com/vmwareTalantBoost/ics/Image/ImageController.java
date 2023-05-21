package com.vmwareTalantBoost.ics.Image;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "image")
public class ImageController {

     private final  ImageService imageService;

     @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public String TryImagga() {
        //return "hello";
        return imageService.ImagaTagsList("https://imagga.com/static/images/tagging/wind-farm-538576_640.jpg");
    }
}
