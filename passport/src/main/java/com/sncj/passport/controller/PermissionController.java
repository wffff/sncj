package com.sncj.passport.controller;

import com.sncj.core.auth_center.dto.PermissionDTO;
import com.sncj.core.auth_center.entity.PermissionEntity;
import com.sncj.core.auth_center.entity.RoleEntity;
import com.sncj.core.auth_center.enums.PermissionTypeEnum;
import com.sncj.core.auth_center.service.IPermissionService;
import com.sncj.core.auth_center.service.IRoleService;
import com.sncj.core.baseconfig.ReturnMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private IPermissionService iPermissionService;
    @Resource
    private IRoleService iRoleService;

    @RequestMapping("/list")
    @ResponseBody
    public ReturnMessage<List<PermissionEntity>> list() {
        List<PermissionEntity> permissionEntities = iPermissionService.listPermissionByConditions();
        return ReturnMessage.success(permissionEntities.size(), permissionEntities);
    }

    @RequestMapping("add")
    @ResponseBody
    public ReturnMessage add(String name, String url, Integer pid, PermissionTypeEnum permissionType) {
        iPermissionService.createPermission(name, url, pid, permissionType);
        return ReturnMessage.success("创建成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public ReturnMessage update(Integer id, String name, String url) {
        iPermissionService.updatePermission(id, name, url);
        return ReturnMessage.success("更新成功");
    }

    @RequestMapping("remove")
    @ResponseBody
    public ReturnMessage remove(Integer id) {
        boolean b = iPermissionService.removePermission(id);
        if (b) {
            return ReturnMessage.success("删除成功");
        } else {
            return ReturnMessage.failed("删除失败,在有子资源的情况下无法删除");
        }
    }

    @RequestMapping("test")
    @ResponseBody
    public ReturnMessage<List<PermissionDTO>> test() {
        List<PermissionDTO> permission = iPermissionService.listTreeByUser("admin");
        return ReturnMessage.success(permission.size(), permission);
    }

    @RequestMapping("getByRoleId")
    @ResponseBody
    public ReturnMessage<List<PermissionEntity>> getByRoleId(Integer id) {
        RoleEntity r = iRoleService.findById(id);
        List<PermissionDTO> p = PermissionDTO.formatTreeDTO(r.getPermission());
        List<PermissionEntity> permissionEntities = PermissionDTO.formatResourceEntity(p);
        List<Integer> list = new ArrayList<>();
        for (PermissionEntity per : permissionEntities) {
            list.add(per.getId());
        }
        List<PermissionEntity> permissionEntitiesAll = iPermissionService.listPermissionByConditions();
        if (null != list && list.size() != 0) {
            for (PermissionEntity per : permissionEntitiesAll) {
                if (list.contains(per.getId())) {
                    per.setEnabled(true);
                }
            }
        }
        return ReturnMessage.success(permissionEntitiesAll.size(), permissionEntitiesAll);
    }


    @RequestMapping("grant")
    @ResponseBody
    public ReturnMessage grant(Integer roleId, Integer permissionId, boolean enabled) {
        ReturnMessage result;
        try {
            RoleEntity r = iRoleService.enabled(roleId, permissionId, enabled);
            result = ReturnMessage.success(1,r);
        } catch (Exception e) {
            result = ReturnMessage.failed(e.getMessage());
        }
        return result;
    }

}
