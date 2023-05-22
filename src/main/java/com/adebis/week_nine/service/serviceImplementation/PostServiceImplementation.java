package com.adebis.week_nine.service.serviceImplementation;

import com.adebis.week_nine.DTO.post.PostDTO;
import com.adebis.week_nine.DTO.post.PostRequestDTO;
import com.adebis.week_nine.enumpackage.Role;
import com.adebis.week_nine.errorpackage.CustomError;
import com.adebis.week_nine.model.Post;
import com.adebis.week_nine.model.User;
import com.adebis.week_nine.repository.PostRepo;
import com.adebis.week_nine.repository.UserRepo;
import com.adebis.week_nine.service.PostService;
import com.adebis.week_nine.utilPactkage.Util;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {

    private PostRepo postRepo;
    private UserRepo userRepo;

    @Autowired
    public PostServiceImplementation(PostRepo postRepo, UserRepo userRepo){
        this.postRepo= postRepo;
        this.userRepo= userRepo;
    }

    public PostDTO createNewPost(PostRequestDTO postRequestDTO, HttpServletRequest request) throws CustomError {
        String id = Util.checkForCurrentUserId(request);


        User user = userRepo.findUserByEmail(id).get();

        Post postNew = new Post(postRequestDTO);
                postNew.setUser(user);

        Post post = postRepo.save(postNew);


        PostDTO response = new PostDTO(post);

        return response;
    }


    public PostDTO editPost(Long postId, PostRequestDTO postDTO ,HttpServletRequest request) throws CustomError {
        String id = Util.checkForCurrentUserId(request);


        User user = userRepo.findUserByEmail(id).get();


       Optional<Post> optionalPost = postRepo.findById(postId);
        if(optionalPost.isEmpty()){


            throw new CustomError("Post id does not exit" ,HttpStatus.NOT_FOUND, HttpStatusCode.valueOf(404));

        }



        Post post = optionalPost.get();

        if(post.getUser().getId() != user.getId()){
            throw new CustomError("You don't have access to edit this post", HttpStatus.UNAUTHORIZED, HttpStatusCode.valueOf(401));
        }

        if(postDTO.getTitle() != null && postDTO.getTitle().length()>0){
            post.setTitle(postDTO.getTitle());
        }
        if(postDTO.getContent() != null && postDTO.getContent().length()>0){
            post.setContent(postDTO.getContent());
        }

        Post newPost  =  postRepo.save(post);

        PostDTO response = new PostDTO(newPost);



        return response;
    }


    public List<PostDTO> getAllPost( ){

        Sort sort = Sort.by("createdTime").descending();
        List<PostDTO> response = postRepo.findAll(sort).stream().map(x->new PostDTO(x)).toList();
        return response;
    }

    public List<PostDTO> getAllPostThatContain(String words){
        List<PostDTO> response = postRepo.findPostsByTitleContainingIgnoreCaseOrderByCreatedTimeDesc(words)
                .stream().map(x->new PostDTO(x)).toList();
        return response;
    }


    public PostDTO getPostById(Long id) throws CustomError {
      Optional<Post> optionalPost =   postRepo.findById(id);
      if(!optionalPost.isPresent()){


          throw new CustomError("Post id does not exit" ,HttpStatus.NOT_FOUND, HttpStatusCode.valueOf(404));

      }

         PostDTO postDTO = new PostDTO( optionalPost.get());
        return postDTO;
    }


   public String deletePostById(Long id, HttpServletRequest request) throws CustomError  {
       String email = Util.checkForCurrentUserId(request);


       User user = userRepo.findUserByEmail(email).get();
       Optional<Post> optionalPost = postRepo.findById(id);
       if(optionalPost.isEmpty()){


           throw new CustomError("Post id does not exit" ,HttpStatus.NOT_FOUND, HttpStatusCode.valueOf(404));

       }

       if(user.getRole() != Role.ADMIN || optionalPost.get().getUser().getId() != user.getId()){

           throw new CustomError("You are not authorize to delete a post" ,HttpStatus.UNAUTHORIZED, HttpStatusCode.valueOf(401));



       }




       postRepo.deleteById(id);

       return "Post successfully deleted";
   }
}
