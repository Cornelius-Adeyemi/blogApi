package com.adebis.week_nine.DTO.post;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDTO {

    @NotBlank(message = "What is the content of your post")
    private String content;

    @NotBlank(message = "What is the title of your post")
    private String title;


}
