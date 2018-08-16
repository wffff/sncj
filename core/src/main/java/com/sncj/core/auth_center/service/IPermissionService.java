package com.sncj.core.auth_center.service;

import com.sncj.core.auth_center.dto.PermissionDTO;
import com.sncj.core.auth_center.entity.PermissionEntity;
import com.sncj.core.auth_center.enums.PermissionTypeEnum;

import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
public interface IPermissionService {
    //分页查询角色
    List<PermissionEntity> listPermissionByConditions();

    //创建角色
    List<PermissionEntity> createPermission(String name, String url, Integer pid, PermissionTypeEnum permissionType);
    //根据用户名查找资源树
    List<PermissionDTO> listTreeByUser(String username);

    //查找所有的大菜单
    List<PermissionEntity> listMenuAndPidNULL();
    //查找某个资源的子菜单
    List<PermissionEntity> listMenuAndPidNOTNULL(Integer permissionId);
    //查找某个资源的子选项卡
    List<PermissionEntity> listTabs(Integer permissionId);
    //查找某个资源的子功能
    List<PermissionEntity> listFunc(Integer permissionId);
}
