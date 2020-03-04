package com.cis.minisocialnetwork.Entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "rating")
    private float rating;
    @NotNull
    @Column(name = "likes_count")
    private float likesCount;
    @NotNull
    @Column(name = "title")
    private String title;
    @NotNull
    @Column(name = "text")
    private String text;
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;

    protected Post(){

    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", rating=" + rating +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", creator=" + creator +
                '}';
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void updateRating(float rating){
        this.rating = (this.rating * this.likesCount + rating) / (this.likesCount + 1);
        this.likesCount += 1;
    }
}
