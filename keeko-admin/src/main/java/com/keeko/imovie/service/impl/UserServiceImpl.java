package com.keeko.imovie.service.impl;

import com.keeko.imovie.entity.User;
import com.keeko.imovie.mapper.UserMapper;
import com.keeko.imovie.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }
}
