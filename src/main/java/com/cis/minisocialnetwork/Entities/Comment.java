package com.cis.minisocialnetwork.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private User creator;
    private String text;
    private Date date;

    protected Comment(){

    }
}
