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
import com.adebis.week_nine.service.PostLikeService;
import com.adebis.week_nine.utilPactkage.Util;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PostLikeServiceImplementation implements PostLikeService {


    private PostRepo postRepo;
    private UserRepo userRepo;
    private PostLikeRepo postLikeRepo;
    private DislikeRepo dislikeRepo;

    @Autowired
    public PostLikeServiceImplementation(PostRepo postRepo,UserRepo userRepo,PostLikeRepo postLikeRepo,
                                        DislikeRepo dislikeRepo){
        this.dislikeRepo=dislikeRepo;
        this.postLikeRepo =postLikeRepo;
        this.postRepo=postRepo;
        this.userRepo =userRepo;

    }


    public String likeAndUnlikePost(Long postId, HttpServletRequest request) throws CustomError {
        String userId = Util.checkForCurrentUserId(request);

        User user = userRepo.findUserByEmail(userId).get();

        Optional<Dislike> optionalDislike = dislikeRepo.findbyUserIdAndPostId(user.getId(), postId);
        if(optionalDislike.isPresent()){
            dislikeRepo.delete(user.getId(), postId);
        }
        Optional<PostLike> optionalPostLike = postLikeRepo.findbyUserIdAndPostId(user.getId(),postId);
        if(optionalPostLike.isPresent()){


            postLikeRepo.delete(user.getId(), postId);

        }else{

            PostLike postLike = new PostLike();
            postLike.setUser(user);
            postLike.setIslike(true);

            Post post = postRepo.findById(postId).get();

            post.getPostLikeList().add(postLike);
            postRepo.save(post);

        }



        return "Done!";
    }
}
