package com.adebis.week_nine.model;

import com.adebis.week_nine.DTO.user.UserDTO;
import com.adebis.week_nine.enumpackage.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Entity
@Table(name = "users" ,
        uniqueConstraints = {@UniqueConstraint(name="unique_email", columnNames = "email")})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends Auditable{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
     private  Long id;

     @Column(nullable = false)
     private String  name;
     @Column(nullable = false)
     private String email;
     @Column(nullable = false)
     private String password;
     @Column(nullable = false)
     private Role role = Role.CLIENT;
     @Column(nullable = false)
     @JsonFormat(pattern = "yyyy-MM-dd")
     private LocalDate dob;

     private Boolean isActive=true;

     private Boolean isEnabled=true;
     @JsonIgnoreProperties("user")
     @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch =FetchType.LAZY )
     private List<Token> token;





    public User(UserDTO userDTO){

        name = userDTO.getName();
        email = userDTO.getEmail();
        password = userDTO.getPassword();
        dob =  LocalDate.parse(userDTO.getDob(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }





}
