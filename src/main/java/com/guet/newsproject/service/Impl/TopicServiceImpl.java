package com.guet.newsproject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.newsproject.entity.Topic;
import com.guet.newsproject.mapper.TopicMapper;
import com.guet.newsproject.service.TopicService;
import org.springframework.stereotype.Service;

/**
 * @author: ZZJ
 * @date: 2023/06/08
 * @desc:
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {
}
