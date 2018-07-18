package com.sncj.passport.service.impl;

import com.sncj.passport.baseconfig.BasePage;
import com.sncj.passport.baseconfig.utils.RegexUtils;
import com.sncj.passport.entity.RoleEntity;
import com.sncj.passport.exception.RoleException;
import com.sncj.passport.repository.IRoleRepository;
import com.sncj.passport.service.IRoleService;
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
