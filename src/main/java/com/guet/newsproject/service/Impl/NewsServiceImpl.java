package com.guet.newsproject.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.newsproject.entity.Comments;
import com.guet.newsproject.entity.News;
import com.guet.newsproject.mapper.CommentsMapper;
import com.guet.newsproject.mapper.NewsMapper;
import com.guet.newsproject.service.NewsService;
import com.guet.newsproject.vo.NewsQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: ZZJ
 * @date: 2023/06/08
 * @desc:
 */
@Transactional
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public List<News> getNewsListByTopicId(int topicId, int number) {
        return baseMapper.getNewsListByTopicId(topicId,number);
    }

    @Override
    public IPage<News> getNewsListByPage(IPage<News> page, NewsQueryVo newsQueryVo) {
        return baseMapper.getNewsListByPage(page,newsQueryVo);
    }

    @Override
    public boolean deleteById(Integer nid) {
        //根据新闻编号 来删除新闻信息 但是首先得确保 新闻的评论也被干掉了
        QueryWrapper<Comments> wrapper = new QueryWrapper<>();
        wrapper.eq("cnid",nid);
        Integer count = commentsMapper.selectCount(wrapper);
//如果有评论就删除评论
        if (count>0){
            int i = commentsMapper.delete(wrapper);
        }
//根据新闻编号删除 新闻信息
        int delete = baseMapper.deleteById(nid);
        if (delete>0){
            return true;
        }
        return false;

    }

    @Override
    public boolean removeById(Integer nid) {
        return false;
    }

    @Override
    public boolean updateById(Integer nid) {
        return false;
    }

}

