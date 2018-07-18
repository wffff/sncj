package com.sncj.passport.service.impl;

import com.sncj.passport.baseconfig.BasePage;
import com.sncj.passport.baseconfig.utils.RegexUtils;
import com.sncj.passport.dto.PermissionDTO;
import com.sncj.passport.entity.PermissionEntity;
import com.sncj.passport.entity.RoleEntity;
import com.sncj.passport.entity.UserEntity;
import com.sncj.passport.enums.PermissionTypeEnum;
import com.sncj.passport.exception.PermissionException;
import com.sncj.passport.repository.IPermissionRepository;
import com.sncj.passport.service.IPermissionService;
import com.sncj.passport.service.IUserService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Danny on 2018/7/9.
 */
@Service
public class PermissionService implements IPermissionService {
    @Resource
    private IPermissionRepository iPermissionRepository;
    @Resource
    private IUserService iUserService;
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

    @Override
    public List<PermissionDTO> listTreeByUser(String username) {
        UserEntity member = iUserService.findByName(username);
        Set<RoleEntity> role = member.getRole();
        Set<PermissionEntity> resource = new HashSet<>();
        role.forEach(r -> resource.addAll(r.getPermission()));
        List<PermissionDTO> permissionDTOS = PermissionDTO.formatTreeDTO(resource);
        ///解决深入遍历死循环问题
        LinkedList<PermissionDTO> ll = new LinkedList();
        permissionDTOS.forEach(res -> {
            if (res.getParentId() != null) {
                ll.add(res);
            }
        });
        permissionDTOS.removeAll(ll);
        return permissionDTOS;
    }

}
