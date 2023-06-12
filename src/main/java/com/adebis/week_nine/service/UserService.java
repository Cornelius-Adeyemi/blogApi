package com.adebis.week_nine.service;

import com.adebis.week_nine.DTO.user.UserDTO;
import com.adebis.week_nine.DTO.user.UserLogin;
import com.adebis.week_nine.DTO.user.UserRegistrationDTO;
import com.adebis.week_nine.errorpackage.CustomError;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    UserDTO login(UserLogin userLogin, HttpServletRequest request) throws Exception;

    UserDTO register(UserRegistrationDTO userRegistrationDTO) throws CustomError;


    String logout(HttpServletRequest request);
}
