package com.sncj.core.auth_center.service;

import com.sncj.core.baseconfig.BasePage;
import com.sncj.core.auth_center.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
public interface IUserService {
    //分页查询用户
    Page<UserEntity> pageUserByConditions(BasePage basePage,String name);
    //查询用户
    List<UserEntity> listUserByConditions();
    //创建用户
    List<UserEntity> createUser(String username, String password);

    UserEntity findByName(String username);

    UserEntity add(String username, String password, String name);
}
