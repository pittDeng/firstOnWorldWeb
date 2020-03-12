package com.dsh.controller;

import com.dsh.App;
import com.dsh.dao.UserMapper;
import com.dsh.entity.CommunityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegisterController {
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/register")
    public String toRegister(){
        return "register";
    }
    @RequestMapping("/register/result")
    public String toRegisterResult(){
        return "registerResult";
    }
    @ResponseBody
    @RequestMapping("/register/do")
    public String Doregister(HttpServletRequest request, HttpServletResponse response){
        if(userMapper==null){
            System.out.println("AutoWired failed");
            userMapper=(UserMapper) App.context.getBean("getUserMapper");
        }
        String userName=request.getParameter("username");
        String password=request.getParameter("password");
        CommunityUser user = new CommunityUser(userName, password);
        userMapper.addUser(user);
        return "success";
    }
}
