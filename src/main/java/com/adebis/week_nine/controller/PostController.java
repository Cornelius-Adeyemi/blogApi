package com.adebis.week_nine.controller;


import com.adebis.week_nine.DTO.post.PostDTO;
import com.adebis.week_nine.DTO.post.PostRequestDTO;
import com.adebis.week_nine.errorpackage.CustomError;
import com.adebis.week_nine.model.Image;
import com.adebis.week_nine.repository.ImageRepo;
import com.adebis.week_nine.service.DislikeService;
import com.adebis.week_nine.service.PostLikeService;
import com.adebis.week_nine.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@Tag(name="Post")
public class PostController {

    private PostService postService;

    private ImageRepo imageRepo;
    private PostLikeService postLikeService;

    private DislikeService dislikeService;

    @Autowired
    public PostController(PostService postService,PostLikeService postLikeService,
                          DislikeService dislikeService,ImageRepo imageRepo){

        this.postService = postService;
        this.postLikeService= postLikeService;
        this.dislikeService =dislikeService;
        this.imageRepo=imageRepo;
    }
    @Operation(summary = "This is an endpoint to create a post")
    @PostMapping("/create-post")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostRequestDTO postDTO, HttpServletRequest request) throws CustomError {
;

        return new ResponseEntity<>(postService.createNewPost(postDTO, request), HttpStatus.CREATED);
    }
    @Operation(summary = "This is an endpoint to delete a post. Only the creator of the post can delete the post")
    @PostMapping("/edit_post/{postId}")
    public ResponseEntity<PostDTO> editPost(@PathVariable("postId") Long postId, @RequestBody PostRequestDTO postDTO, HttpServletRequest request) throws CustomError {
        ;

        return new ResponseEntity<>(postService.editPost(postId,postDTO, request), HttpStatus.ACCEPTED);
    }
    @Operation(summary = "This is an endpoint to get all post")
    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPost(){

        return new ResponseEntity<>(postService.getAllPost(), HttpStatus.ACCEPTED);
    }
    @Operation(summary = "This is an endpoint to get posts that contain some key words")
    @GetMapping("/get-posts")
    public ResponseEntity<List<PostDTO>> getAllPostThatContain(@RequestParam("word") String word){

        return new ResponseEntity<>(postService.getAllPostThatContain(word), HttpStatus.ACCEPTED);
    }
    @Operation(summary = "This is an endpoint to get a specific post by id")
    @GetMapping("/get-post/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") Long postId) throws CustomError {
        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.ACCEPTED);
    }
    @Operation(summary = "This is an endpoint to delete a specific post by id")
    @GetMapping("/delete-post/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable("postId") Long postId, HttpServletRequest request) throws CustomError {
        return new ResponseEntity<>(postService.deletePostById(postId, request), HttpStatus.ACCEPTED);
    }
    @Operation(summary = "This is an endpoint to like and reverse like a post")
    @GetMapping("/like-unlike/{postId}")
    public ResponseEntity<String> likeAndUnlikePost(@PathVariable("postId") Long postId, HttpServletRequest request) throws CustomError {
        return new ResponseEntity<>(postLikeService.likeAndUnlikePost(postId,request), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "This is an endpoint to dislike and reverse dislike a post")
    @GetMapping("/dislike-undislike/{postId}")
    public ResponseEntity<String> dislikeAndUndislike(@PathVariable("postId") Long postId, HttpServletRequest request) throws CustomError {
        return new ResponseEntity<>(dislikeService.dislikeAndUndislikePost(postId, request), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "This is an endpoint to get an image")
    @GetMapping("/image/{image_id}/{image_name}")
    public ResponseEntity<byte[]> getImaege(@PathVariable("image_id") long image_id){
        Image image = imageRepo.findById(image_id).get();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(image.getImage(),headers, HttpStatus.OK);

    }

}
