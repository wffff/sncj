package com.sncj.passport.service.impl;

import com.sncj.passport.baseconfig.BasePage;
import com.sncj.passport.baseconfig.RegexUtils;
import com.sncj.passport.entity.PermissionEntity;
import com.sncj.passport.entity.RoleEntity;
import com.sncj.passport.exception.PermissionException;
import com.sncj.passport.exception.RoleException;
import com.sncj.passport.repository.IPermissionRepository;
import com.sncj.passport.repository.IRoleRepository;
import com.sncj.passport.service.IPermissionService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
@Service
public class PermissionService implements IPermissionService {
    @Resource
    private IPermissionRepository iPermissionRepository;

    @Override
    public Page<PermissionEntity> pagePermissionByConditions(BasePage basePage) {
        PermissionEntity permissionEntity=new PermissionEntity();
        permissionEntity.setDel(false);
        return iPermissionRepository.findAll(Example.of(permissionEntity),basePage.getRequestPage());
    }

    @Override
    public List<PermissionEntity> listPermissionByConditions() {
        PermissionEntity permissionEntity=new PermissionEntity();
        permissionEntity.setDel(false);
        return iPermissionRepository.findAll(Example.of(permissionEntity));
    }

    @Override
    public List<PermissionEntity> createPermission(String name, String description, String url, Integer pid, String method) {
        if (!RegexUtils.notNull(name)){
            throw new PermissionException("权限名字为空");
        }
        if (!RegexUtils.isRole(name)){
            throw new PermissionException("权限名字需要以ROLE_开头");
        }
        PermissionEntity permissionEntity=new PermissionEntity();
        permissionEntity.setName(name);
        permissionEntity.setDescription(description);
        permissionEntity.setUrl(url);
        permissionEntity.setPid(pid);
        permissionEntity.setMethod(method);
        return iPermissionRepository.saveAll(List.of(permissionEntity));
    }
}
