package com.adebis.week_nine.DTO.post;


import com.adebis.week_nine.DTO.comment.CommentDTO;
import com.adebis.week_nine.DTO.imageDTO.ImageDTO;
import com.adebis.week_nine.DTO.likeanddislike.DislikeDTO;
import com.adebis.week_nine.DTO.likeanddislike.LikeDTO;
import com.adebis.week_nine.DTO.user.UserDTO;
import com.adebis.week_nine.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostDTO {

    private Long id;

    private String content;

    private String title;

    private LocalDateTime createdTime;

    private List<DislikeDTO> dislikeList;

    private List<LikeDTO> postLikeList;

    private List<CommentDTO> commentList;

    private List<ImageDTO> image;

    private UserDTO user;


    public PostDTO(Post post){

        id = post.getId();
        content = post.getContent();
        title = post.getTitle();
        createdTime = post.getCreatedTime();
        dislikeList = post.getDislikeList().stream().map(x->new DislikeDTO(x)).toList();
        postLikeList = post.getPostLikeList().stream().map(x->new LikeDTO(x)).toList();
        commentList = post.getCommentList().stream().map(x->new CommentDTO(x)).toList();
        user =  new UserDTO(post.getUser()) ;
        image= post.getPostImages().stream().map(ImageDTO::new).toList();

    }

}
