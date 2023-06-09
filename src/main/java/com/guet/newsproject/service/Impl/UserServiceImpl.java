package com.guet.newsproject.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.newsproject.entity.Users;
import com.guet.newsproject.mapper.UsersMapper;
import com.guet.newsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: ZZJ
 * @date: 2023/06/08
 * @desc:
 */
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UsersMapper, Users> implements UserService {

    @Autowired
    UsersMapper usersMapper;


    @Override
    public Users login(String username, String password) {
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("uname",username);
        wrapper.eq("upwd",password);
        Users users = usersMapper.selectOne(wrapper);
        return users;
    }
}
