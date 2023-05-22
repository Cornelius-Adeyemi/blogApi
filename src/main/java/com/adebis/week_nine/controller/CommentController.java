package com.adebis.week_nine.controller;


import com.adebis.week_nine.DTO.comment.CommentDTO;
import com.adebis.week_nine.DTO.comment.CommentRequestDTO;
import com.adebis.week_nine.errorpackage.CustomError;
import com.adebis.week_nine.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Tag(name="Comment")
public class CommentController {

    private CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }
    @Operation(summary = "An end point to comment on a post. Provide the id of the post to comment on in the url")
    @PostMapping("/comment/{postId}")
    public ResponseEntity<String> createPost(@PathVariable("postId") Long postId,
                                             @RequestBody CommentRequestDTO commentDTO, HttpServletRequest request) throws CustomError {

        return new ResponseEntity<>(commentService.makeComment(postId, commentDTO,request), HttpStatus.CREATED);
    }
    @Operation(summary = "This is an end point to delete a comment, provide the id of the comment to delete via the url")
     @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<String>  deleteComment(@PathVariable("commentId") Long commentId, HttpServletRequest request) throws IllegalAccessException, CustomError {
        return new ResponseEntity<>(commentService.deleteComment(commentId,request),HttpStatus.ACCEPTED);
    }


}
