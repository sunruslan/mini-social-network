package com.cis.minisocialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostDto {
    Long postId;
    String title;
    String content;
    String nickname;
    String profilePicUrl;
}