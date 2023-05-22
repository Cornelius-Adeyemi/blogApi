package com.adebis.week_nine.DTO.likeanddislike;

import com.adebis.week_nine.DTO.user.UserDTO;
import com.adebis.week_nine.model.PostLike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeDTO {

    private Long id;

    private Boolean islike;

    private UserDTO user;


    public LikeDTO(PostLike like){
        this.id = like.getId();
        this.islike = like.getIslike();
       this.user= new UserDTO(like.getUser());
    }
}
