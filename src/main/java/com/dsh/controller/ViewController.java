package com.dsh.controller;

import com.alibaba.fastjson.JSONObject;
import com.dsh.App;
import com.dsh.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ViewController {
    @RequestMapping("/test")
    @ResponseBody
    public String test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        System.out.println("In this router");
        try{
            System.out.println(httpServletRequest.getParameter("name"));
            System.out.println(httpServletRequest.getParameterValues("name"));
            System.out.println(Arrays.toString(httpServletRequest.getParameterValues("name")));
            if (httpServletRequest.getParameter("name").equals("dsh")){
                httpServletResponse.setHeader("nstatus","success");
                return "success";
            }
        }catch (Exception e){

        }
        return "fail";

    }
    @RequestMapping("/home")
    public String welcome(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            User user=(User)principal;
            model.addAttribute("username",user.getUsername()==null?null:user.getUsername());
        }catch (Exception e){

        }
        return "home";
    }
    @RequestMapping("/")
    public String nolocation(Model model){
        model.addAttribute("msg","guest");
        model.addAttribute("god","Jesus");
        return "forward:/home";
    }
    @RequestMapping("/else")
    public String elseLocation(){
        return "redirect:/home";
    }
    @RequestMapping("/login")
    public String login(){
        ConfigurableApplicationContext context = App.context;
        context.getBean("getUserMapper");
        return "login";
    }
    @RequestMapping("/auth")
    public String userPage(Model model){
        try{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String str=((User) principal).getUsername();
            model.addAttribute("name",str);
        }catch (Exception e){

        }
        return "userPage";
    }
    @RequestMapping("/logerr")
    public String logerr(){
        return "logerr";
    }
    @RequestMapping("/person")
    public String person(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user=(User)principal;
        model.addAttribute("message","The user name of your account is"+user.getUsername()+"\n"+"The password is "+user.getPassword());
        return "personalInfo";
    }
    private static List<Article> articles=new ArrayList<>();
    @RequestMapping("/writearticle")
    public String writeArticle(){
        return "writeArticle";
    }
    /**
     *
     * @param id  id number of the article requested
     * @return JsonString of the article
     */
    @ResponseBody
    @RequestMapping("/get/{id}")
    public String getArticle(@PathVariable("id")int id){
        Article article = articles.get(id);
        String s = JSONObject.toJSONString(article);
        System.out.println(s);
        return s;
    }
    @RequestMapping("/add")
    @ResponseBody
    public String addArticle(HttpServletRequest request,HttpServletResponse response){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user=(User)principal;
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        System.out.println("title: "+title);
        System.out.println("content: " + content);
        //添加一篇文章，在数据库中的
        articles.add(new Article(articles.size(),user.getUsername(),title,content));
        return "{\"id\":"+Integer.toString(articles.size()-1)+"}";
    }
    @RequestMapping("/read/*")
    public String readArticle(){
        return "readArticle";
    }
}
