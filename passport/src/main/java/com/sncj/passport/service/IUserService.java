package com.sncj.passport.service;

import com.sncj.passport.entity.UserEntity;

import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
public interface IUserService {
    List<UserEntity> findAll();
    //创建用户
//    List<UserEntity> createUser(String username,String password);
}
