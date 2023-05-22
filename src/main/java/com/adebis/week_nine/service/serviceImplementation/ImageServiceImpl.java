package com.adebis.week_nine.service.serviceImplementation;

import com.adebis.week_nine.errorpackage.CustomError;
import com.adebis.week_nine.model.Image;
import com.adebis.week_nine.model.Post;
import com.adebis.week_nine.model.User;
import com.adebis.week_nine.repository.ImageRepo;
import com.adebis.week_nine.repository.PostRepo;
import com.adebis.week_nine.repository.UserRepo;
import com.adebis.week_nine.service.ImageService;
import com.adebis.week_nine.utilPactkage.Util;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {


    private PostRepo postRepo;
    private ImageRepo imageRepo;

    private UserRepo userRepo;

    private ServletContext servletContext;

    @Autowired
    public ImageServiceImpl(PostRepo postRepo,ImageRepo imageRepo,
                            ServletContext servletContext,UserRepo userRepo){
        this.postRepo = postRepo;
        this.imageRepo= imageRepo;
        this.servletContext = servletContext;
        this.userRepo=userRepo;
    }

    public String saveImage(MultipartFile[] files, Long post_id, HttpServletRequest request) throws CustomError {
        String email = Util.checkForCurrentUserId(request);


        Optional<Post> optionalPost = postRepo.findById(post_id);

        if(optionalPost.isEmpty()){
            throw new CustomError("invalid post id", HttpStatus.BAD_REQUEST, HttpStatusCode.valueOf(400));
        }

        User user = userRepo.findUserByEmail(email).get();


        String contextPath = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();

        Post post = optionalPost.get();

        if(post.getUser().getId() != user.getId()){
            throw new CustomError("FORBIDDEN", HttpStatus.FORBIDDEN, HttpStatusCode.valueOf(403));
        }

        for(MultipartFile file: files){
            String fileName;
            byte[] imageArray;
            String fileType;

            if(!file.getContentType().startsWith("image/")){

                throw new CustomError("Invalid file type", HttpStatus.BAD_REQUEST, HttpStatusCode.valueOf(400));
            }
             fileName = file.getOriginalFilename();
            fileType = file.getContentType();
            try {
                imageArray = file.getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Image image = new Image();
            image.setImage(imageArray);
            image.setName(fileName);
            image.setPost(post);
            System.out.println("here************************2");
            Image savedImage = imageRepo.save(image);
            String url = contextPath + "/post" +  "/image/" + savedImage.getId() +   "/" + savedImage.getName();
            savedImage.setUrl(url);
             imageRepo.save(savedImage);
            System.out.println("here************************3");
        }

//      return   postRepo.findById(post_id).get();

        return "success";


    }
}
