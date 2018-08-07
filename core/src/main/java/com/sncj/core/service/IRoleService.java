package com.sncj.core.service;

import com.sncj.core.baseconfig.BasePage;
import com.sncj.core.entity.RoleEntity;
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
}
