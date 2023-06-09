package com.guet.newsproject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.newsproject.entity.Comments;
import com.guet.newsproject.mapper.CommentsMapper;
import com.guet.newsproject.service.CommentsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: ZZJ
 * @date: 2023/06/08
 * @desc:
 */
@Service
@Transactional
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {
}

