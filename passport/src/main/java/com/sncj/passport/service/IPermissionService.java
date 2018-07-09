package com.sncj.passport.service;

import com.sncj.passport.entity.PermissionEntity;

import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
public interface IPermissionService {
    List<PermissionEntity> findAll();
}
