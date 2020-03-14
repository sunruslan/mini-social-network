package com.cis.minisocialnetwork.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import sun.net.ftp.FtpDirEntry;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "user_entity")
public class User extends AuditModel {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Automatically generated id of the user")
    private Long id;
    @NotNull
    @Column(name = "nickname")
    @Size(max=30)
    @ApiModelProperty(notes = "Nickname of the user for logging in")
    private String nickname;
    @ElementCollection(fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "Roles that user is going to have")
    List<Role> roles;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    @JsonManagedReference
    @JsonIgnore
    @ApiModelProperty(notes = "Variable linking the user with his user profile")
    private UserProfile userProfile;
    @OneToMany(mappedBy = "to")
    private List<Followers> followers;
    @OneToMany(mappedBy="from")
    private List<Followers> following;


    public List<Role> getRoles() {
        return roles;
    }

    public String getNickname() {
        return nickname;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }
}
