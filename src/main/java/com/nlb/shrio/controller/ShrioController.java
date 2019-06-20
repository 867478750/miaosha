package com.nlb.shrio.controller;

import com.nlb.shrio.pojo.student;
import com.nlb.shrio.service.userService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class ShrioController {
    @RequestMapping("/test1")
    public ModelAndView test1(){
        ModelAndView mv =new ModelAndView("test");
        student name =new student();
        name.setId(1);
        name.setName("nlb");
        mv.addObject("name",name);
        return mv;
    }

    @RequestMapping("user/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("user/update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping("/loginIn")
    public ModelAndView loginIn(@RequestParam("msg") String msg){
        ModelAndView login = new ModelAndView("login");
        login.addObject("msg",msg);
        return login;
    }

    @RequestMapping("/loginIn1")
    public String loginIn1(){
        return "login";
    }

    @RequestMapping("/login")
    public ModelAndView login(String name, String password){
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        try {
            subject.login(token);//无异常登陆成功
            return new ModelAndView("test");
        } catch (UnknownAccountException e) {
            //有异常登陆失败
            ModelAndView modelAndView = new ModelAndView("redirect:/loginIn");
            modelAndView.addObject("msg","用户名不存在");
            return modelAndView;
        } catch (IncorrectCredentialsException e){
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("msg","密码错误");
            return modelAndView;
        }

    }

    @RequestMapping("/user/test")
    @ResponseBody
    public String test00(){
        return "test";
    }

    @RequestMapping("/Unauth")
    public String Unauth(){
        return "Unauth";
    }

    @RequestMapping("/out")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "login";
    }

    @Autowired
    userService userService;


}
