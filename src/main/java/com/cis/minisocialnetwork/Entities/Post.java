package com.cis.minisocialnetwork.Entities;

import org.graalvm.compiler.api.replacements.ClassSubstitution;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

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
    @Column(name = "title")
    private String title;
    @NotNull
    @Column(name = "text")
    private String text;
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    @ManyToOne
    @JoinColumn
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
}
