package com.vmwareTalantBoost.ics.Image.Controllers;

import com.vmwareTalantBoost.ics.Image.Classes.Tag;
import com.vmwareTalantBoost.ics.Image.Services.ImaggaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "image")
public class TagsController {
    private final ImaggaService imaggaService;

    public TagsController(ImaggaService imaggaService) {
        this.imaggaService = imaggaService;
    }

    @GetMapping
    public Set<Tag> imageTagsListFromImagga(String image_url) {
        return imaggaService.getTagsFromImage(image_url);
    }
}
