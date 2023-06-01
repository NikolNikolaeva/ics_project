package com.vmwareTalantBoost.ics.Image.Controllers;

import com.vmwareTalantBoost.ics.Image.Classes.Tag;
import com.vmwareTalantBoost.ics.Image.Services.ImaggaService;
import com.vmwareTalantBoost.ics.Image.Services.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "image")
public class TagsController {
    private final ImaggaService imaggaService;
    private final TagService tagService;

    public TagsController(ImaggaService imaggaService, TagService tagService) {
        this.imaggaService = imaggaService;
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> imageTagsListFromImagga() {
        return tagService.getAllTags();
    }

    @GetMapping({"str"})
    public List<Tag> getTagsWithString(String str) {
        return tagService.getTagsWithStr(str);
    }
}
