package com.guet.newsproject.controller;

/**
 * @author: ZZJ
 * @date: 2023/06/08
 * @desc:
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guet.newsproject.entity.News;
import com.guet.newsproject.entity.Topic;
import com.guet.newsproject.service.NewsService;
import com.guet.newsproject.service.TopicService;
import com.guet.newsproject.vo.TopicQueryVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 主题列表
 */
@Controller
@RequestMapping("/topic")
public class TopicController {
    @Resource
    private TopicService topicService;

    @Resource
    private NewsService newsService;

    /**
     * 主题列表
     *
     * @param topicQueryVo
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String manager(TopicQueryVo topicQueryVo, Model model) {
        IPage<Topic> page = topicService.page(new Page<Topic>
                (topicQueryVo.getPageNo(), topicQueryVo.getPageSize()));
        model.addAttribute("page", page);
        return "topic/list";
    }

    /**
     * 去到添加主题页面
     *
     * @return
     */
    @RequestMapping("/add.html")
    public String toAdd() {
        return "topic/add";
    }

    /**
     * 添加主题
     *
     * @param topic
     * @return
     */
    @RequestMapping("/add")
    public String add(Topic topic) {
        if (topicService.save(topic)) {
            return "redirect:/topic/list";
        }
        return "redirect:/topic/add.html";
    }

    /**
     * 去到编辑主题页面
     *
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String toEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("topic", topicService.getById(id));
        return "topic/edit";
    }

    /**
     * 编辑主题
     *
     * @param topic
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Topic topic) {
        if (topicService.updateById(topic)) {
            return "redirect:/topic/list";
        }
        return "redirect:/topic/edit/" + topic.getTid();
    }

    /**
     * 删除主题
     *
     * @param id
     * @param response
     */
    @RequestMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Integer id, HttpServletResponse
            response) {
        try {
            response.setCharacterEncoding("GBK");
            QueryWrapper<News> queryWrapper = new QueryWrapper<News>();
            queryWrapper.eq("ntid", id);
            if (newsService.count(queryWrapper) > 0) {
                response.getWriter().print("<script>alert('该主题下存在新闻信息，无法删除 ');location.href=' / topic / list ';</script>");
            } else {
//调用删除的方法
                if (topicService.removeById(id)) {
                    response.getWriter().print("<script>alert('删除成功');location.href=' / topic / list ';</script>");
                } else {
                    response.getWriter().print("<script>alert('删除失败');location.href=' / topic / list ';</script>");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
