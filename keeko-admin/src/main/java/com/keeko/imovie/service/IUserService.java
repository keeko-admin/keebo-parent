package com.keeko.imovie.service;

import com.keeko.imovie.entity.User;

public interface IUserService {

    User selectByPrimaryKey(String id);
}
