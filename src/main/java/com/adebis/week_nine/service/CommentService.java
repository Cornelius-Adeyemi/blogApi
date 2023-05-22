package com.adebis.week_nine.service;

import com.adebis.week_nine.DTO.comment.CommentDTO;
import com.adebis.week_nine.DTO.comment.CommentRequestDTO;
import com.adebis.week_nine.errorpackage.CustomError;
import jakarta.servlet.http.HttpServletRequest;

public interface CommentService {
    String makeComment(Long postId, CommentRequestDTO commentDTO, HttpServletRequest request) throws CustomError;
    String deleteComment(Long commentId, HttpServletRequest request) throws  CustomError;

}
