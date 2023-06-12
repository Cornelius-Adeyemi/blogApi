package com.adebis.week_nine.controller;


import com.adebis.week_nine.DTO.user.UserDTO;
import com.adebis.week_nine.DTO.user.UserLogin;
import com.adebis.week_nine.DTO.user.UserRegistrationDTO;
import com.adebis.week_nine.errorpackage.CustomError;
import com.adebis.week_nine.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name="User")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=  userService;
    }
    @Operation(summary = "An endpoint for a user to sign up ")
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody UserRegistrationDTO userRegistrationDTO) throws CustomError {

        return new ResponseEntity<>(userService.register(userRegistrationDTO), HttpStatusCode.valueOf(201));
    }

    @Operation(summary = "This is an endpoint for user to login")
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserLogin userDTO, HttpServletRequest request) throws Exception {

        return new ResponseEntity<>(userService.login(userDTO, request), HttpStatusCode.valueOf(200));
    }

    @PatchMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){

        return new ResponseEntity<>(userService.logout(request), HttpStatus.ACCEPTED);
    }

}
