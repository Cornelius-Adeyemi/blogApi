package com.adebis.week_nine.security;

import com.adebis.week_nine.model.User;
import com.adebis.week_nine.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService  implements UserDetailsService {

    private UserRepo userRepo;

    @Autowired
    public MyUserDetailService(UserRepo userRepo){
        this.userRepo =userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> usersOptional =  userRepo.findUserByEmail(username);

        if(!usersOptional.isPresent()){
            throw new UsernameNotFoundException("Invalid email address");
        }


        UserDetails userDetails = new MyUserDetails(usersOptional.get());


        return userDetails;
    }
}
