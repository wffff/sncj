package com.sncj.passport.service;

import com.sncj.passport.baseconfig.BasePage;
import com.sncj.passport.dto.PermissionDTO;
import com.sncj.passport.entity.PermissionEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
public interface IPermissionService {
    //分页查询角色
    Page<PermissionEntity> pagePermissionByConditions(BasePage basePage);

    //查询角色
    List<PermissionEntity> listPermissionByConditions();

    //创建角色
    List<PermissionEntity> createPermission(String name, String description,String url,Integer pid,String method);

    List<PermissionDTO> listTreeByUser(String username);
}
