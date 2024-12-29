package ics.Image.Classes;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Comment {

    @Id
    @SequenceGenerator(
            name="comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence"
    )
    private long id;

    @Column(name="commentText")
    private String commentText;
    @Column(name="author")
    private String author;
    @Column(name="date")
    private Date date;

    public Comment(String commentText, String author, Date date) {
        this.commentText = commentText;
        this.author = author;
        this.date = date;
    }

    public Comment() {
    }

    public String getCommentText() {
        return commentText;
    }
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}

