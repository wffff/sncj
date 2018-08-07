package com.sncj.passport.controller;
import com.sncj.core.baseconfig.BasePage;
import com.sncj.core.baseconfig.ReturnMessage;
import com.sncj.core.auth_center.entity.PermissionEntity;
import com.sncj.core.auth_center.service.IPermissionService;
import org.springframework.data.domain.Page;
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

    @RequestMapping("/page")
    @ResponseBody
    public ReturnMessage<List<PermissionEntity>> page(BasePage basePage) {
        Page<PermissionEntity> permissionEntities = iPermissionService.pagePermissionByConditions(basePage);
        return ReturnMessage.success((int) permissionEntities.getTotalElements(), permissionEntities.getContent());
    }

}
