package com.guet.newsproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.newsproject.entity.Users;

/**
 * @author: ZZJ
 * @date: 2023/06/08
 * @desc:
 */
public interface UserService extends IService<Users> {

    Users login(String username,String password);
}
