package com.guet.newsproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.newsproject.entity.Comments;
import com.guet.newsproject.entity.News;
import com.guet.newsproject.vo.NewsQueryVo;

import java.util.List;

/**
 * @author: ZZJ
 * @date: 2023/06/08
 * @desc:
 */
public interface NewsService extends IService<News> {

    /**
     * 通过新闻分类来获取新闻
     * @param topicId
     * @param number
     * @return
     */
    public List<News> getNewsListByTopicId(int topicId, int number);

    /**
     * 分页查询新闻列表
     * @param page
     * @param newsQueryVo
     * @return
     */
    IPage<News> getNewsListByPage(IPage<News> page, NewsQueryVo newsQueryVo);

    boolean deleteById(Integer nid);

    boolean removeById(Integer nid);

    boolean updateById(Integer nid);

}
