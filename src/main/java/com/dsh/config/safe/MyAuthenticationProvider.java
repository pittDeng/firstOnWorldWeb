package com.dsh.config.safe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication==null)return null;
        System.out.println("auth " + authentication);
        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        try{
            if(authentication.getPrincipal() instanceof String){
                System.out.println("authentication: "+authentication.getCredentials().toString());
                System.out.println("password"+userDetails.getPassword());
                if (userDetails.getPassword().equals(authentication.getCredentials().toString())){
                    //请注意下面这句语句必须这么写，将authorities传入authentication中，否则每次请求都会调用这个函数
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),userDetails.getAuthorities());
                    return authenticationToken;
                }
            }else{
                System.out.println("auth" + authentication);
                System.out.println("principal: "+authentication.getPrincipal());
                return new UsernamePasswordAuthenticationToken( userDetails,userDetails.getPassword());
            }

        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
