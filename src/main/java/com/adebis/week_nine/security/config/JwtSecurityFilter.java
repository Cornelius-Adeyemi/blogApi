package com.adebis.week_nine.security.config;

import com.adebis.week_nine.errorpackage.CustomError;
import com.adebis.week_nine.model.Token;
import com.adebis.week_nine.repository.TokenRepo;
import com.adebis.week_nine.security.MyUserDetailService;
import com.adebis.week_nine.security.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtSecurityFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final MyUserDetailService userDetailsService;

    private final TokenRepo tokenRepo;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String authorizationHeader = request.getHeader("Authorization");

        String username= null;
        String token =null;

        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer")){
            String[] authorizationArr = authorizationHeader.split(" ");
            token=  authorizationArr[authorizationArr.length -1];
            username = jwtUtil.extractUsername(token);

        }

        if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

           boolean databaseToken = tokenRepo.findByValue(token).map(x->x.isInvalid()).orElse(true);

            if(jwtUtil.validateToken(token, userDetails) && !databaseToken){

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(userDetails.getUsername() ,
                        null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }

        }
        filterChain.doFilter(request,response);

    }
}
