package com.adebis.week_nine.DTO.user;


import com.adebis.week_nine.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String token;

    private String name;


    private String email;
    private String password;
    private String role;




    private String dob;

    public UserDTO(User user){
        name = user.getName();
        email = user.getEmail();
        dob = user.getDob().toString();
        role= user.getRole().name();

    }

    public UserDTO(UserRegistrationDTO userRegistrationDTO){
        name = userRegistrationDTO.getName();
        email = userRegistrationDTO.getEmail();
        password = userRegistrationDTO.getPassword();
        dob= userRegistrationDTO.getDob();
    }


}
