package com.sncj.core.auth_center.service.impl;

import com.sncj.core.baseconfig.BasePage;
import com.sncj.core.baseconfig.utils.RegexUtils;
import com.sncj.core.auth_center.entity.RoleEntity;
import com.sncj.core.auth_center.exception.RoleException;
import com.sncj.core.auth_center.repository.IRoleRepository;
import com.sncj.core.auth_center.service.IRoleService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
@Service
public class RoleService implements IRoleService {
    @Resource
    private IRoleRepository iRoleRepository;

    @Override
    public Page<RoleEntity> pageRoleByConditions(BasePage basePage) {
        RoleEntity roleEntity=new RoleEntity();
        roleEntity.setDel(false);
        return iRoleRepository.findAll(Example.of(roleEntity),basePage.getRequestPage());
    }

    @Override
    public List<RoleEntity> listRoleByConditions() {
        RoleEntity roleEntity=new RoleEntity();
        roleEntity.setDel(false);
        return iRoleRepository.findAll(Example.of(roleEntity));
    }

    @Override
    public List<RoleEntity> createRole(String name, String description) {
        if (!RegexUtils.notNull(name)){
            throw new RoleException("角色名字为空");
        }
        RoleEntity roleEntity=new RoleEntity();
        roleEntity.setName(name);
        roleEntity.setDescription(description);
        return iRoleRepository.saveAll(List.of(roleEntity));
    }
}