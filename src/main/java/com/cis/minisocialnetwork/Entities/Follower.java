package com.cis.minisocialnetwork.Entities;

import javax.persistence.*;

@Entity
@Table(name = "followers")
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_user_fk")
    private User from;

    @ManyToOne
    @JoinColumn(name = "to_user_fk")
    private User to;

    protected Follower(){

    }

    public Follower(User from, User to){
        this.from = from;
        this.to = to;
    }

}
