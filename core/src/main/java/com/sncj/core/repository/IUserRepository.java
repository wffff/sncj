package com.sncj.core.repository;


import com.sncj.core.baseconfig.BaseRepository;
import com.sncj.core.entity.UserEntity;

/**
 * Created by Danny on 2018/7/9.
 */
public interface IUserRepository extends BaseRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
    //创建用户
}
