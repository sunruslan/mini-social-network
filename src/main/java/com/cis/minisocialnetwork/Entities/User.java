package com.cis.minisocialnetwork.Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name") private String firstName;
    @Column(name = "last_name") private String lastName;
    @Column(name = "phone_number") private String phoneNumber;
    @Column private String city;
    @Column private String country;
    @Column private String gender;
    @Column private Date birthday;
    @Column private String nickname;
    @Column(name = "password_hash") private String passwordHash;
    @Column private String email;
    private List<Post> posts;
    private List<Comment> comments;

    protected User() {
    }
}
