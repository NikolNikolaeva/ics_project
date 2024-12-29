package ics.Image.Repositories;

import ics.Image.Classes.Comment;
import ics.Image.Classes.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT s FROM Comment s where s.id = ?1")
    Optional<Comment> findCommentById(long id);

}
