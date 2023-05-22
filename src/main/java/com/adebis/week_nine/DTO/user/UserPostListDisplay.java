package com.adebis.week_nine.DTO.user;

import com.adebis.week_nine.model.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPostListDisplay {


    private String title;
    private String content;

    private int noOfLike;

    private int noOfDislike;

    private int noOfComment;

    private LocalDateTime createdTime;


    public UserPostListDisplay(Post post){

        title= post.getTitle();
        content=  post.getContent();
        noOfDislike = post.getDislikeList().size();
        noOfLike = post.getPostLikeList().size();
        noOfComment = post.getCommentList().size();
    }
}
