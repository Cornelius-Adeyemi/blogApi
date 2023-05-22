package com.adebis.week_nine.service;

import com.adebis.week_nine.DTO.post.PostDTO;
import com.adebis.week_nine.DTO.post.PostRequestDTO;
import com.adebis.week_nine.errorpackage.CustomError;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface PostService {

    PostDTO createNewPost(PostRequestDTO postRequestDTO, HttpServletRequest request) throws CustomError;

    PostDTO editPost(Long postId, PostRequestDTO postDTO ,HttpServletRequest request) throws  CustomError;

    List<PostDTO> getAllPost( );
    List<PostDTO> getAllPostThatContain(String words);
    PostDTO getPostById(Long id) throws CustomError;
    String deletePostById(Long id, HttpServletRequest request) throws  CustomError;

}
