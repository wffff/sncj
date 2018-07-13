package com.sncj.passport.repository;

import com.sncj.passport.baseconfig.BaseRepository;
import com.sncj.passport.entity.UserEntity;

/**
 * Created by Danny on 2018/7/9.
 */
public interface IUserRepository extends BaseRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
    //创建用户
}
