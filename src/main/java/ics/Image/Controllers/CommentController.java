package ics.Image.Controllers;


import ics.Image.Classes.Comment;
import ics.Image.Classes.Image;
import ics.Image.Repositories.CommentRepository;
import ics.Image.Services.CommentService;
import ics.Image.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "comments")
public class CommentController {

    final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService, ImageService imageService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }
}
