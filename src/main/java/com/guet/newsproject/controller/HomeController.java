package com.guet.newsproject.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guet.newsproject.entity.News;
import com.guet.newsproject.service.NewsService;
import com.guet.newsproject.service.TopicService;
import com.guet.newsproject.vo.NewsQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: ZZJ
 * @date: 2023/06/08
 * @desc:
 */
@Controller
public class HomeController {
    @Autowired
    NewsService newsService;

    @Autowired
    TopicService topicService;

    @RequestMapping(value = {"/", "/index.html", "/home.html"})
    public String index(Model model, NewsQueryVo newsQueryVo) {
        //先查询出来左侧的分类标题
        List<News> newsList1 = newsService.getNewsListByTopicId(1, 6);
        List<News> newsList2 = newsService.getNewsListByTopicId(2, 6);
        List<News> newsList3 = newsService.getNewsListByTopicId(3, 6);
        model.addAttribute("newsList1", newsList1);
        model.addAttribute("newsList2", newsList2);
        model.addAttribute("newsList3", newsList3);
        //创建分页查询对象 帮助分页查询信息
        //设置页码和分页的 数量
        IPage<News> page = new Page<>(newsQueryVo.getPageNo(),newsQueryVo.getPageSize());
        page = newsService.getNewsListByPage(page, newsQueryVo);
        model.addAttribute("page",page);
        //设置分页信息参数
        model.addAttribute("newVo",newsQueryVo);
        return "index";
    }

    @RequestMapping("/manager")
    public String manager(NewsQueryVo newsQueryVo,Model model){
        //管理员模块主页只需要查询出新闻列表信息就ok
        IPage<News> page = new Page<>
                (newsQueryVo.getPageNo(),newsQueryVo.getPageSize());
        newsService.getNewsListByPage(page,newsQueryVo);
        model.addAttribute("page",page);
        return "admin";
    }

    /**
     * 去到添加新闻页面
     * @param model
     * @return
     */
    @RequestMapping("/add.html")
    public String toAdd(Model model){
        //查询主题列表
        model.addAttribute("topicList", topicService.list());
        return "news/add";
    }
}
