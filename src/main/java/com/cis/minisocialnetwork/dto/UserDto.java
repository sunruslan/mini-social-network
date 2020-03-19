package com.cis.minisocialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto {
    String nickname;
    String about;
    boolean gender;
    boolean isFollowing;
    String location;
    String profilePicUrl;

    public String getNickname() {
        return nickname;
    }
}
