package com.cis.minisocialnetwork.Model;

import javax.persistence.*;

@Entity
public class Followers {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="from_user_fk")
    private User from;

    @ManyToOne
    @JoinColumn(name="to_user_fk")
    private User to;

    public Followers() {};

    public Followers(User from, User to) {
        this.from = from;
        this.to = to;
    }
}
