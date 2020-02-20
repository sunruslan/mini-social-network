package com.cis.minisocialnetwork.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    @Size(max = 25)
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 25)
    @NotNull
    private String lastName;

    @Column(name = "phone_number")
    @Size(max = 25)
    private String phoneNumber;

    @Column
    @Size(max = 25) private String city;

    @Column
    @Size(max = 25) private String country;

    @Column
    @Size(max = 25) private String gender;


    @Column private Date birthday;

    @Column
    @Size(max = 25)
    @NotNull
    private String nickname;

    @Column
    @Size(max = 25)
    @NotNull
    private String email;

    private User() {
    }

    public User(String firstName, String lastName, String nickname, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
