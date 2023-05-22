package com.adebis.week_nine.DTO.user;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {

    @NotBlank(message = "email field should not be blank")
    private String email;


    @NotBlank(message = "password field should not be blank")
    private String password;

}
