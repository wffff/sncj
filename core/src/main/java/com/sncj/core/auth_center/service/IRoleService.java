package com.sncj.core.auth_center.service;

import com.sncj.core.auth_center.entity.RoleEntity;
import com.sncj.core.baseconfig.BasePage;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
public interface IRoleService {
    //分页查询角色
    Page<RoleEntity> pageRoleByConditions(BasePage basePage);

    //查询角色
    List<RoleEntity> listRoleByConditions();

    //创建角色
    List<RoleEntity> createRole(String name, String description);

    RoleEntity findById(Integer id);

    RoleEntity update(RoleEntity r);

    RoleEntity enabled(Integer roleId, Integer permissionId, boolean enabled);

    List<RoleEntity> findAll();
}
