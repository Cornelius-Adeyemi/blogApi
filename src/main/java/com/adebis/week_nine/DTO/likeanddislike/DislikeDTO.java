package com.adebis.week_nine.DTO.likeanddislike;

import com.adebis.week_nine.DTO.user.UserDTO;
import com.adebis.week_nine.model.Dislike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DislikeDTO {
    private Long id;

    private Boolean isDislike;

    private UserDTO user;

    public DislikeDTO(Dislike dislike){
        id= dislike.getId();
        isDislike = dislike.getIsDislike();
        user = new UserDTO(dislike.getUser());


    }
}
