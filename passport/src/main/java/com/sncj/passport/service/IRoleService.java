package com.sncj.passport.service;

import com.sncj.passport.entity.RoleEntity;

import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
public interface IRoleService {
    List<RoleEntity> findAll();
}
