package com.adebis.week_nine.controller;


import com.adebis.week_nine.errorpackage.CustomError;
import com.adebis.week_nine.model.Post;
import com.adebis.week_nine.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("image")
@Tag(name="Post's image")
public class ImageController {


private ImageService imageService;

public ImageController(ImageService imageService){
    this.imageService = imageService;
}

    @Operation(summary = "This is an endpoint to an image to a post, provide the id of the post to add the image in the url")
@PostMapping("addimage/post/{post_id}")
public ResponseEntity<String> addImage(@PathVariable("post_id") Long post_id, @RequestParam("files")MultipartFile[] files, HttpServletRequest request) throws CustomError {

    return new ResponseEntity<>(imageService.saveImage(files, post_id,request), HttpStatus.CREATED);
}



}
