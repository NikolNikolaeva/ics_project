package ics.Image.Services;

import ics.Image.Classes.Comment;
import ics.Image.Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    final CommentRepository commentRepository;
    final ImageService imageService;

    @Autowired
    public CommentService(CommentRepository commentRepository, ImageService imageService) {
        this.commentRepository = commentRepository;
        this.imageService = imageService;
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByImageId(long imageId) {
        return commentRepository.findAllByImageId(imageId);
    }
}
