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

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Follower{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
