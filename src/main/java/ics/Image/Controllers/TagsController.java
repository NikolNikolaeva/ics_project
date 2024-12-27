package ics.Image.Controllers;

import ics.Image.Classes.Tag;
import ics.Image.Services.ImaggaService;
import ics.Image.Services.TagService;
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
