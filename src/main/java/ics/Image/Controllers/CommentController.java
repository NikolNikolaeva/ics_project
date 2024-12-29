package ics.Image.Controllers;


import ics.Image.Classes.Comment;
import ics.Image.Repositories.CommentRepository;
import ics.Image.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "comments")
public class CommentController {

    final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(path = "/imageId")
    public List<Comment> getComment(@RequestParam("imageId") long imageId) {
        return commentService.getCommentsByImageId(imageId);
    }

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }
}
