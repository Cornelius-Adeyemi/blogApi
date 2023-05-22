package com.adebis.week_nine.DTO.comment;

import com.adebis.week_nine.DTO.user.UserDTO;
import com.adebis.week_nine.model.Comment;
import com.adebis.week_nine.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;

    private String content;

    private LocalDateTime createdTime;

    private UserDTO user;

     public CommentDTO(Comment comment){
         id= comment.getId();
         content = comment.getContent();
         createdTime = comment.getCreatedTime();
         user= new UserDTO(comment.getUser());

     }

}
