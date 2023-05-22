package com.adebis.week_nine.service.serviceImplementation;

import com.adebis.week_nine.errorpackage.CustomError;
import com.adebis.week_nine.model.Dislike;
import com.adebis.week_nine.model.Post;
import com.adebis.week_nine.model.PostLike;
import com.adebis.week_nine.model.User;
import com.adebis.week_nine.repository.DislikeRepo;
import com.adebis.week_nine.repository.PostLikeRepo;
import com.adebis.week_nine.repository.PostRepo;
import com.adebis.week_nine.repository.UserRepo;
import com.adebis.week_nine.service.DislikeService;
import com.adebis.week_nine.utilPactkage.Util;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DislikeServiceImplementation implements DislikeService  {

    private PostRepo postRepo;
    private UserRepo userRepo;
    private PostLikeRepo postLikeRepo;
    private DislikeRepo dislikeRepo;

    @Autowired
    public DislikeServiceImplementation(PostRepo postRepo,UserRepo userRepo,PostLikeRepo postLikeRepo,
                                        DislikeRepo dislikeRepo){
        this.dislikeRepo=dislikeRepo;
        this.postLikeRepo =postLikeRepo;
        this.postRepo=postRepo;
        this.userRepo =userRepo;

    }


    public String dislikeAndUndislikePost(Long postId, HttpServletRequest request) throws CustomError {

        String email = Util.checkForCurrentUserId(request);
        User user = userRepo.findUserByEmail(email).get();

        Optional<PostLike> optionalPostlike = postLikeRepo.findbyUserIdAndPostId(user.getId(), postId);
        if(optionalPostlike.isPresent()){
            postLikeRepo.delete(user.getId(), postId);
        }
        Optional<Dislike> optionalDislike = dislikeRepo.findbyUserIdAndPostId(user.getId(), postId);
        if(optionalDislike.isPresent()){


            dislikeRepo.delete(user.getId(), postId);

        }else{

            Dislike dislike = new Dislike();
            dislike.setUser(user);
            dislike.setIsDislike(true);

            Post post = postRepo.findById(postId).get();

            post.getDislikeList().add(dislike);
            postRepo.save(post);

        }



        return "Done!";
    }


}
