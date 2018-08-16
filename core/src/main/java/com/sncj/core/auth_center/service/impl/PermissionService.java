package com.sncj.core.auth_center.service.impl;

import com.sncj.core.auth_center.dto.PermissionDTO;
import com.sncj.core.auth_center.entity.PermissionEntity;
import com.sncj.core.auth_center.entity.RoleEntity;
import com.sncj.core.auth_center.entity.UserEntity;
import com.sncj.core.auth_center.enums.PermissionTypeEnum;
import com.sncj.core.auth_center.exception.PermissionException;
import com.sncj.core.auth_center.repository.IPermissionRepository;
import com.sncj.core.auth_center.service.IPermissionService;
import com.sncj.core.auth_center.service.IUserService;
import com.sncj.core.baseconfig.utils.RegexUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
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
    public List<PermissionEntity> listPermissionByConditions() {
        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setDel(false);
        return iPermissionRepository.findAll(Example.of(permissionEntity), new Sort(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<PermissionEntity> createPermission(String name, String url, Integer pid, PermissionTypeEnum permissionType) {
        PermissionEntity permissionEntity = new PermissionEntity();
        PermissionEntity parent=iPermissionRepository.findOne(pid);
        //如果是最大的菜单
        if (pid==null&&permissionType==PermissionTypeEnum.MENU){
            List<PermissionEntity> list=listMenuAndPidNULL();
            if (null==list||list.size()==0){
                permissionEntity.setModuleSort(1);
            }else {
                permissionEntity.setModuleSort(list.get(0).getModuleSort() + 1);
            }
            permissionEntity.setMenuSort(0);
            permissionEntity.setTabSort(0);
            permissionEntity.setFuncSort(0);
        }else if (permissionType==PermissionTypeEnum.MENU){
            List<PermissionEntity> list=listMenuAndPidNOTNULL(pid);
            if (null==list||list.size()==0){
                permissionEntity.setMenuSort(1);
            }else {
                permissionEntity.setMenuSort(list.get(0).getMenuSort()+1);
            }
            permissionEntity.setModuleSort(parent.getModuleSort());
            permissionEntity.setTabSort(0);
            permissionEntity.setFuncSort(0);
        }else if (permissionType==PermissionTypeEnum.TABS){
            List<PermissionEntity> list=listTabs(pid);
            if (null==list||list.size()==0){
                permissionEntity.setTabSort(1);
            }else {
                permissionEntity.setTabSort(list.get(0).getTabSort()+1);
            }
            permissionEntity.setModuleSort(parent.getModuleSort());
            permissionEntity.setMenuSort(parent.getMenuSort());
            permissionEntity.setFuncSort(0);
        }else if (permissionType==PermissionTypeEnum.FUNC){
            List<PermissionEntity> list=listFunc(pid);
            if (null==list||list.size()==0){
                permissionEntity.setFuncSort(1);
            }else {
                permissionEntity.setFuncSort(list.get(0).getFuncSort()+1);
            }
            permissionEntity.setModuleSort(parent.getModuleSort());
            permissionEntity.setMenuSort(parent.getMenuSort());
            permissionEntity.setTabSort(parent.getTabSort());

        }
        permissionEntity.setName(name);
        permissionEntity.setType(permissionType);
        permissionEntity.setUrl(url);
        permissionEntity.setPid(pid);
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

    @Override
    public List<PermissionEntity> listMenuAndPidNULL() {
        return iPermissionRepository.listByCondition(true,null,PermissionTypeEnum.MENU);
    }

    @Override
    public List<PermissionEntity> listMenuAndPidNOTNULL(Integer permissionId) {
        return iPermissionRepository.listByCondition(null,permissionId,PermissionTypeEnum.MENU);
    }

    @Override
    public List<PermissionEntity> listTabs(Integer permissionId) {
        return iPermissionRepository.listByCondition(null,permissionId,PermissionTypeEnum.TABS);
    }

    @Override
    public List<PermissionEntity> listFunc(Integer permissionId) {
        return iPermissionRepository.listByCondition(null,permissionId,PermissionTypeEnum.FUNC);
    }

}
