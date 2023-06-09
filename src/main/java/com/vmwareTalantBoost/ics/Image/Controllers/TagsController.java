package com.vmwareTalantBoost.ics.Image.Controllers;

import com.vmwareTalantBoost.ics.Image.Classes.Tag;
import com.vmwareTalantBoost.ics.Image.Services.ImaggaService;
import com.vmwareTalantBoost.ics.Image.Services.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "tags")
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
    public List<Tag> getTagsWithString(@RequestParam String str) {
        return tagService.getTagsWithStr(str);
    }
}
