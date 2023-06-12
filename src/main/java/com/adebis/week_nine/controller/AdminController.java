package com.adebis.week_nine.controller;


import com.adebis.week_nine.DTO.user.UserDTO;
import com.adebis.week_nine.DTO.user.UserRegistrationDTO;
import com.adebis.week_nine.errorpackage.CustomError;
import com.adebis.week_nine.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/account")
@RequiredArgsConstructor
@Tag(name="Admin")
public class AdminController {

    private final AdminService adminService;
    @Operation(summary = "This is an endpoint for the manager to signup")
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> adminSignup(@RequestBody UserRegistrationDTO userRegistrationDTO) throws CustomError {

        return new ResponseEntity<>( adminService.signup(userRegistrationDTO), HttpStatus.CREATED);
    }
    @Operation(summary = "This is an endpoint to get the list of all users")
    @GetMapping("/get-users")
    public ResponseEntity<List<UserDTO>> getAllUsers( ){
        System.out.println("here*************");
        return new ResponseEntity<>(adminService.getAllUsers(), HttpStatus.ACCEPTED);
    }
    @Operation(summary = "This is an endpoint to block a user")
    @GetMapping("/block-user/{userId}")
    public ResponseEntity<String> blockUser(@PathVariable("userId") Long userId ) throws CustomError {
        System.out.println("Security ********************");
        return new ResponseEntity<>(adminService.blockUser(userId), HttpStatus.ACCEPTED);
    }

}
