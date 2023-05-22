package com.adebis.week_nine.DTO.user;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {


    @NotBlank(message = "Name field should not be empty")
    private String name;

    @NotBlank(message = "email filed should not be empty")
    private String email;

    @NotBlank(message = "password field should not be empty")
    private String password;

    @NotBlank(message = "please enter date of birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String dob;



}
