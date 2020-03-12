package com.dsh.config.safe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SafeConfiguration extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/","/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll();
//    }
    public static final String [] permitAllPatterns={"/login","/register","/register/do","/register/result","/js/**/*.js","/pic/*","/videos/*","/*.ico"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(permitAllPatterns).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/loginp").permitAll(). loginPage("/login").successHandler(new AuthenticationSuccessHandler() {
                    @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                HttpSessionRequestCache cache = new HttpSessionRequestCache();
                try {
                    SavedRequest request = cache.getRequest(httpServletRequest, httpServletResponse);
                    String url = request.getRedirectUrl();
                    if (url.lastIndexOf("login.html") > 0)
                        httpServletResponse.sendRedirect(url.replace("login.html", ""));
                    else {
                        httpServletResponse.sendRedirect(url);
                    }
                }catch (NullPointerException e){
                    httpServletResponse.sendRedirect("http://localhost/");
                }
            }
        });
        http.logout().logoutUrl("/logout").permitAll().logoutSuccessUrl("/home");
       // http.authorizeRequests().antMatchers("favicon.ico").permitAll();
    }
    @Autowired
    AuthenticationProvider myAuthenticationProvider;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder()).withUser("admin").password(new BCryptPasswordEncoder().encode("12345")).roles("ADMIN");
        auth.authenticationProvider(myAuthenticationProvider);
        }
}
