package com.adebis.week_nine.service;

import com.adebis.week_nine.errorpackage.CustomError;
import jakarta.servlet.http.HttpServletRequest;

public interface PostLikeService {
    String likeAndUnlikePost(Long postId, HttpServletRequest request) throws  CustomError;
}
