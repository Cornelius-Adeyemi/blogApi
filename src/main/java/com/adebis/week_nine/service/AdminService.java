package com.adebis.week_nine.service;

import com.adebis.week_nine.DTO.user.UserDTO;
import com.adebis.week_nine.DTO.user.UserRegistrationDTO;
import com.adebis.week_nine.errorpackage.CustomError;

import java.util.List;

public interface AdminService {
    
    UserDTO signup(UserRegistrationDTO userRegistrationDTO) throws CustomError;

    List<UserDTO> getAllUsers();

    String blockUser(Long userId) throws CustomError;
}
