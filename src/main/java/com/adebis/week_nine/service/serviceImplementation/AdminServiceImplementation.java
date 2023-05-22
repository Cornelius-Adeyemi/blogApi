package com.adebis.week_nine.service.serviceImplementation;

import com.adebis.week_nine.DTO.user.UserDTO;
import com.adebis.week_nine.DTO.user.UserRegistrationDTO;
import com.adebis.week_nine.enumpackage.Role;
import com.adebis.week_nine.errorpackage.CustomError;
import com.adebis.week_nine.model.User;
import com.adebis.week_nine.repository.UserRepo;
import com.adebis.week_nine.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDTO signup(UserRegistrationDTO userRegistrationDTO) throws CustomError {

       Optional<User> findUser = userRepo.findUserByEmail(userRegistrationDTO.getEmail());

        if(findUser.isPresent()){
            throw new CustomError("Email already exist", HttpStatus.BAD_REQUEST, HttpStatusCode.valueOf(400));
        }

        User user = new User(new UserDTO(userRegistrationDTO));
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setRole(Role.ADMIN);

        User savedUser  =  userRepo.save(user);

        return new UserDTO(savedUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream().map(user -> new UserDTO(user) ).toList();
    }

    @Override
    public String blockUser(Long userId) throws CustomError {
        System.out.println("************"+userId);

        System.out.println(userId);
        Optional<User> searchedUser = userRepo.findById(userId);
        if(searchedUser.isEmpty()){
            throw new CustomError("Error, user id does not exit", HttpStatus.BAD_REQUEST, HttpStatusCode.valueOf(400));
        }
        User user = searchedUser.get();
        user.setIsActive(false);
        userRepo.save(user);

        return "You've blocked "+ user.getName();
    }
}
