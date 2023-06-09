package com.guet.newsproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guet.newsproject.entity.Comments;
import com.guet.newsproject.entity.News;
import com.guet.newsproject.vo.NewsQueryVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: ZZJ
 * @date: 2023/06/08
 * @desc:
 */
public interface NewsMapper extends BaseMapper<News> {

    //分页查询新闻列表
    IPage<News> getNewsListByPage(@Param("page")IPage<News> page, @Param("news") NewsQueryVo newsQueryVo);

    @Select("select * from news where ntid = #{topicId} order by ncreateDate desc limit ${number}")
    List<News> getNewsListByTopicId(@Param("topicId") int topicId, @Param("number") int number);

}
