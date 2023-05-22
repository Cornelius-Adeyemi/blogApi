package com.adebis.week_nine.service.serviceImplementation;

import com.adebis.week_nine.DTO.comment.CommentDTO;
import com.adebis.week_nine.DTO.comment.CommentRequestDTO;
import com.adebis.week_nine.enumpackage.Role;
import com.adebis.week_nine.errorpackage.CustomError;
import com.adebis.week_nine.model.Comment;
import com.adebis.week_nine.model.Post;
import com.adebis.week_nine.model.User;
import com.adebis.week_nine.repository.CommentRepo;
import com.adebis.week_nine.repository.PostRepo;
import com.adebis.week_nine.repository.UserRepo;
import com.adebis.week_nine.service.CommentService;
import com.adebis.week_nine.utilPactkage.Util;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService {

   CommentRepo commentRepo;
   UserRepo userRepo;
   PostRepo postRepo;

   @Autowired
   public CommentServiceImplementation(CommentRepo commentRepo,UserRepo userRepo, PostRepo postRepo){
       this.commentRepo = commentRepo;
       this.userRepo =userRepo;
       this.postRepo =postRepo;
   }


    public String makeComment(Long postId, CommentRequestDTO commentDTO, HttpServletRequest request) throws CustomError {
       String userEmail = Util.checkForCurrentUserId(request);

       Post post = postRepo.findById(postId).orElseThrow(()->new NullPointerException("No post with such Id"));

       User user  = userRepo.findUserByEmail(userEmail).get();

       Comment comment = new Comment(commentDTO);
       comment.setUser(user);
       post.getCommentList().add(comment);
       postRepo.save(post);

      // Comment newComment = commentRepo.findCommentByContentAndUser_Id(commentDTO.getContent(), user.getId()).get();


    return "Done";

    }


    public String deleteComment(Long commentId, HttpServletRequest request) throws CustomError {

       String userEmail = Util.checkForCurrentUserId(request);

      User user = userRepo.findUserByEmail(userEmail).get();
       Optional<Comment> optionalComment = commentRepo.findById(commentId);
       if(!optionalComment.isPresent()){

           throw new CustomError("No comment with such Id", HttpStatus.BAD_REQUEST, HttpStatusCode.valueOf(400));


       }
       Comment comment = optionalComment.get();

       if(comment.getUser().getId().equals(user.getId()) ){

       }else if (user.getRole() == Role.ADMIN){

       }else{

           throw new CustomError("You don't have the permission to do this comment", HttpStatus.UNAUTHORIZED, HttpStatusCode.valueOf(401));


       }

       commentRepo.deleteById(commentId);

       return "deleted successfully";
    }

}
