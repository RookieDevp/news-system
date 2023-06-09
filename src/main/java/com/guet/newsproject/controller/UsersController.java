package com.guet.newsproject.controller;

import com.guet.newsproject.entity.Users;
import com.guet.newsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author: ZZJ
 * @date: 2023/06/08
 * @desc:
 */
@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(String userName, String password, HttpSession session){
        Users login = userService.login(userName, password);
        if (login != null) {
            System.out.println("登录成功!!!!!");
            session.setAttribute("loginUser",login);
        }
        return "redirect:/index.html";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }
}
