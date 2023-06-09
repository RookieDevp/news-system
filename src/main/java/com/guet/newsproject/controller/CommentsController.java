package com.guet.newsproject.controller;

import com.guet.newsproject.entity.Comments;
import com.guet.newsproject.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author: ZZJ
 * @date: 2023/06/08
 * @desc:
 */
@Controller
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    CommentsService commentsService;

    @RequestMapping("/add")
    public String add(Comments comments, HttpServletResponse response) {
        comments.setCdate(new Date());
        if (commentsService.save(comments)) {
            return "redirect:/news/detail/" + comments.getCnid();
        }
        return "redirect:/news/detail/" + comments.getCnid();
    }
}
