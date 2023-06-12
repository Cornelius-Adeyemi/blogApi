package com.adebis.week_nine.service.serviceImplementation;

import com.adebis.week_nine.DTO.user.UserDTO;
import com.adebis.week_nine.DTO.user.UserLogin;
import com.adebis.week_nine.DTO.user.UserRegistrationDTO;
import com.adebis.week_nine.errorpackage.CustomError;
import com.adebis.week_nine.model.Token;
import com.adebis.week_nine.model.User;
import com.adebis.week_nine.repository.TokenRepo;
import com.adebis.week_nine.repository.UserRepo;
import com.adebis.week_nine.security.MyUserDetailService;
import com.adebis.week_nine.security.jwt.JwtUtil;
import com.adebis.week_nine.service.UserService;
import com.adebis.week_nine.utilPactkage.Util;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserImplementation implements UserService {

    private PasswordEncoder passwordEncoder;
    private  UserRepo userRepo;

    private UserDetailsService userDetailService;

    private JwtUtil jwtUtil;
    private TokenRepo tokenRepo;


    private AuthenticationManager authenticationManager;

    @Autowired
    public UserImplementation(UserRepo userRepo, PasswordEncoder passwordEncoder,
                              AuthenticationManager authenticationManager, MyUserDetailService userDetailService,
                              JwtUtil jwtUtil, TokenRepo tokenRepo){

        this.userRepo =userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil= jwtUtil;
        this.tokenRepo = tokenRepo;
    }

    public UserDTO login(UserLogin userDTO, HttpServletRequest request) throws Exception {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getEmail(),
                userDTO.getPassword());

        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (BadCredentialsException e){
            throw new Exception(" Invalid login details");
        }
        Util.checkForCurrentUserId(request);
        UserDetails userDetails = userDetailService.loadUserByUsername(userDTO.getEmail());

        String token = jwtUtil.generateToken(userDetails);


        User user = userRepo.findUserByEmail(userDTO.getEmail()).get();

        invalidateToken(user.getId());
        Token tokenEn = Token.builder()
                .value(token)
                .user(user)
                .isInvalid(false)
                .build();

        tokenRepo.save(tokenEn);

        UserDTO myUserDto = new UserDTO(user);

        myUserDto.setToken(token);




        return myUserDto;

    }


    public UserDTO register(UserRegistrationDTO userRegistrationDTO) throws CustomError {

        UserDTO userDTO = new UserDTO(userRegistrationDTO);

        if(userDTO.getEmail().equals("") || userDTO.getPassword().equals("")|| userDTO.getDob()==null ||userDTO.getName().equals("")){

            throw new CustomError("No field should be left empty" ,HttpStatus.BAD_REQUEST, HttpStatusCode.valueOf(400));
        }
        if(userDTO.getEmail() ==null || userDTO.getPassword()==null|| userDTO.getName()==null){

            throw new CustomError("No field should be left empty" ,HttpStatus.BAD_REQUEST, HttpStatusCode.valueOf(400));
        }

        Optional<User> optionalUser =  userRepo.findUserByEmail(userDTO.getEmail());
        if(optionalUser.isPresent()){

            throw new CustomError("Email already exit" ,HttpStatus.BAD_REQUEST, HttpStatusCode.valueOf(400));

        }
        String password = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(password);

        User user = userRepo.save(new User(userDTO));

        UserDTO response = new UserDTO(user);

        return response;
    }

    @Override
    public String logout(HttpServletRequest request) {
        String principal = Util.checkForCurrentUserId(request);
        User user = userRepo.findUserByEmail(principal).get();
        invalidateToken(user.getId());
        return "Successfull";
    }


    public void invalidateToken(Long userId){
        List<Token> listofTokens = tokenRepo.findAllByUser_Email(userId);
        listofTokens= listofTokens.stream().map(token -> {
            token.setInvalid(true);
            return token;
        }).toList();

        tokenRepo.saveAll(listofTokens);
//        System.out.println(listofTokens);

    }



}
