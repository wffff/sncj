package com.sncj.passport.controller;

import com.sncj.core.auth_center.entity.PermissionEntity;
import com.sncj.core.auth_center.enums.PermissionTypeEnum;
import com.sncj.core.auth_center.service.IPermissionService;
import com.sncj.core.baseconfig.ReturnMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private IPermissionService iPermissionService;

    @RequestMapping("/list")
    @ResponseBody
    public ReturnMessage<List<PermissionEntity>> list() {
        List<PermissionEntity> permissionEntities = iPermissionService.listPermissionByConditions();
        return ReturnMessage.success(permissionEntities.size(), permissionEntities);
    }

    @RequestMapping("add")
    @ResponseBody
    public ReturnMessage add(String name, String url, Integer pid, PermissionTypeEnum permissionType){
        iPermissionService.createPermission(name,url,pid,permissionType);
        return ReturnMessage.success("创建成功");
    }

    @RequestMapping("test")
    @ResponseBody
    public ReturnMessage<List<PermissionEntity>> test(){
        List<PermissionEntity> permissionEntities = iPermissionService.listMenuAndPidNULL();
        return ReturnMessage.success(permissionEntities.size(),permissionEntities);
    }

}
