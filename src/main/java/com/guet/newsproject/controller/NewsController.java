package com.guet.newsproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guet.newsproject.entity.Comments;
import com.guet.newsproject.entity.News;
import com.guet.newsproject.service.CommentsService;
import com.guet.newsproject.service.NewsService;
import com.guet.newsproject.service.TopicService;
import com.guet.newsproject.vo.NewsQueryVo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: ZZJ
 * @date: 2023/06/08
 * @desc:
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsService newsService;
    @Autowired
    CommentsService commentsService;

    @Autowired
    TopicService topicService;

    @RequestMapping("/detail/{nid}")
    public String detail(@PathVariable("nid") Integer nid, Model model) {
        //先查询出来左侧的分类标题
        List<News> newsList1 = newsService.getNewsListByTopicId(1, 6);
        List<News> newsList2 = newsService.getNewsListByTopicId(2, 6);
        List<News> newsList3 = newsService.getNewsListByTopicId(3, 6);
        //将数据存入模型中
        model.addAttribute("newsList1", newsList1);
        model.addAttribute("newsList2", newsList2);
        model.addAttribute("newsList3", newsList3);
        //根据id拿出新闻详细信息
        News news = newsService.getById(nid);
        model.addAttribute("news", news);
        //获取评论列表
        QueryWrapper<Comments> wrapper = new QueryWrapper<>();
        //根据新闻编号查询新闻信息
        wrapper.eq("cnid", nid);
        List<Comments> commentsList = commentsService.list(wrapper);
        model.addAttribute("commentsList", commentsList);
        return "news/detail";
    }


    @RequestMapping("/manager")
    public String manager(NewsQueryVo newsQueryVo, Model model) {
        //管理员模块主页只需要查询出新闻列表信息就ok
        IPage<News> page = new Page<>
                (newsQueryVo.getPageNo(), newsQueryVo.getPageSize());
        newsService.getNewsListByPage(page, newsQueryVo);
        model.addAttribute("page", page);
        return "admin";
    }

    /**
     * 去到添加新闻页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add.html")
    public String toAdd(Model model) {
        //查询主题列表
        model.addAttribute("topicList", topicService.list());
        return "news/add";
    }

    @RequestMapping("/add")
    public String add(News news, @PathVariable("file") MultipartFile file) {
        try {
            System.out.println(file);
            //先判断文件是否为空
            if (!file.isEmpty()) {
                System.out.println("文件不为空,开始提交上传");
                //文件不为空,开始提交上传
                //设置一个文件的提交路径
                String path = "F:/temp/upload/";
                //获取旧文件名
                String originalFilename = file.getOriginalFilename();
                //获取文件拓展名
                String extension = FilenameUtils.getExtension(originalFilename);
                //定义新文件名
                String newFileName = UUID.randomUUID().toString().replace("'",
                        "") + "." + extension;
                //为了解决同一个文件夹下面文件过多的问题，我们使用日期作为文件夹管理器
                String dataPath = new SimpleDateFormat("yyyyMMdd").format(new Date());
                //组装最后的文件名
                String finalName = dataPath + "/" + newFileName;
                //创建文件对象
                File destFile = new File(path, finalName);
                //判断文件夹是否存在 不存在则创建对应的文件夹
                if (!destFile.getParentFile().exists()) {
                    destFile.getParentFile().mkdir();
                }
                //将文件保存到磁盘
                file.transferTo(destFile);
                //设置图片的存放路径
                news.setNpicPath(finalName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        news.setNcreateDate(new Date());
        if ((newsService.save(news))) {
            return "redirect:/news/manager";
        }
        return "redirect:/news/add.html";
    }


    @RequestMapping("/edit")
    public String edit(News news, MultipartFile file) {
        try {
//判断是否有文件
            if (!file.isEmpty()) {
                String path = "F:/temp/upload/";
//获取旧名称
                String originalFilename = file.getOriginalFilename();
//获取扩展名
                String extension = FilenameUtils.getExtension(originalFilename);
//重命名文件名
                String newFileName = UUID.randomUUID().toString().replace("- ", "") + "." + extension;
//为了解决同一个文件夹下文件过多的问题，使用日期作为文件夹管理
                String datePath = new SimpleDateFormat("yyyyMMdd").format(new
                        Date());
//组装最终的文件名
                String finalName = datePath + "/" + newFileName;
//创建文件对象
                File destFile = new File(path, finalName);
//判断文件夹是否存在，文件夹不存在则创建该文件夹
                if (!destFile.getParentFile().exists()) {
                    destFile.getParentFile().mkdirs();
                }
//将文件保存到磁盘
                file.transferTo(destFile);
//图片路径
                news.setNpicPath(finalName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        news.setNmodifyDate(new Date());
        if (newsService.updateById(news)) {
            return "redirect:/news/manager";
        }
        return "redirect:/news/edit/" + news.getNid();
    }

    @RequestMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Integer id, HttpServletResponse
            response){
        try {
            response.setCharacterEncoding("GBK");
            if(newsService.removeById(id)){
                response.getWriter().print("<script>alert('删除成功');location.href='/news/manager';</script>");
            }
            response.getWriter().print("<script>alert('删除失败');location.href='/news/manager';</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
