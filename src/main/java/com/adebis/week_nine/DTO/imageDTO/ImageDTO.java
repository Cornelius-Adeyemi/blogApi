package com.adebis.week_nine.DTO.imageDTO;


import com.adebis.week_nine.model.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {

    private Long id;
    private String imageName;
    private String imageUrl;

    public ImageDTO(Image image){
        id= image.getId();
        imageName = image.getName();
        imageUrl=image.getUrl();
    }

}
