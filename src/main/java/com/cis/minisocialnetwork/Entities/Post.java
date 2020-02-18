package com.cis.minisocialnetwork.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column private String title;
    @Column private String text;
    @Column private int rating;
    private User creator;
    private List<Comment> comments;

    protected Post(){

    }
}
