package com.sncj.passport.controller;

import com.sncj.passport.baseconfig.BasePage;
import com.sncj.passport.baseconfig.ReturnMessage;
import com.sncj.passport.entity.PermissionEntity;
import com.sncj.passport.service.IPermissionService;
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
