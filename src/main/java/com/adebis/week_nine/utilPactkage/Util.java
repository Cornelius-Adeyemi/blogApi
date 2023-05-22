package com.adebis.week_nine.utilPactkage;

import com.adebis.week_nine.security.MyUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Util {



    public static String checkForCurrentUserId(HttpServletRequest request){
         String principal =(String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        System.out.println("********************" );

        return principal;
    }
}
