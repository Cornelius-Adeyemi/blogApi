package com.adebis.week_nine.service;

import com.adebis.week_nine.errorpackage.CustomError;
import com.adebis.week_nine.model.Post;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String saveImage(MultipartFile[] files, Long post_id, HttpServletRequest request) throws CustomError;
}
