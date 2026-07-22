package org.belen.rolebasedaccess.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.belen.rolebasedaccess.serviceImpl.JwtService;
import org.belen.rolebasedaccess.serviceImpl.UserPrinciple;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorHeader = request.getHeader("Authorization");

        //        System.out.println(authorHeader+"\n");

        String token=null;
        String userName= null;

//        // Skip filter if no Bearer token structure is found
        if(authorHeader==null || !authorHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        token = authorHeader.substring(7);
        userName = jwtService.extractUserName(token);

//        System.out.println(token+"\n"+userName+"\n");

        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

            if(jwtService.isTokenValid(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);


//                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                UserPrinciple principal = (UserPrinciple) authentication.getPrincipal();
//                System.out.println(authentication.isAuthenticated());
//                System.out.println(principal.getUsername());
//                System.out.println(principal.getPassword());
//                System.out.println(principal.getAuthorities());

//                System.out.println(SecurityContextHolder.getContext().getAuthentication());

            }
        }

        filterChain.doFilter(request,response);

    }
}
