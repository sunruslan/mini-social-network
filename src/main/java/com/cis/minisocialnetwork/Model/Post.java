package com.cis.minisocialnetwork.Model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@SqlResultSetMapping(
        name = "findAllDataMapping",
        classes = @ConstructorResult(
                targetClass = com.cis.minisocialnetwork.dto.PostDto.class,
                columns = {
                        @ColumnResult(name = "id",type=Long.class),
                        @ColumnResult(name = "title",type= String.class),
                        @ColumnResult(name = "content",type=String.class),
                        @ColumnResult(name = "nickname",type=String.class),
                        @ColumnResult(name = "profile_Pic_Url",type=String.class)
                }
        )
)


@Data
@EqualsAndHashCode
@Entity
@Table(name = "posts")
public class Post extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "This is the auto generated post id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @ApiModelProperty(notes = "Heading or title of the post")
    private String title;

    @NotNull
    @Lob
    @ApiModelProperty(notes = "The url of the content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_prof_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("user_prof_id")
    @ApiModelProperty(notes = "Userid of the person who posted the post")
    private UserProfile userProfile;

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}