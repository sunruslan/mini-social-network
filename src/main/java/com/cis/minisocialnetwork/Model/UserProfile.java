package com.cis.minisocialnetwork.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@EqualsAndHashCode
@Table(name="Userprofile")
public class UserProfile extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Automatically generated id of the userprofile")
    private Long Id;

    @NotNull
    @Size(max = 250)
    @ApiModelProperty(notes = "Information about the user")
    private String about;

    @NotNull
    @Size(max = 200)
    @ApiModelProperty(notes = "Url for the profile pic")
    private String profilePicUrl;

    @NotNull
    @ApiModelProperty(notes = "Gender represeted in binary terms")
    private boolean gender;

    @NotNull
    @Size(max = 50)
    @ApiModelProperty(notes = "Location of the user from where he has logged in")
    private String location;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ApiModelProperty(notes = "Property linking the userprofile with the user")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }
}