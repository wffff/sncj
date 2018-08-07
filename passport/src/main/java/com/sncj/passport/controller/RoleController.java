package com.sncj.passport.controller;

import com.sncj.core.baseconfig.BasePage;
import com.sncj.core.baseconfig.ReturnMessage;
import com.sncj.core.auth_center.entity.RoleEntity;
import com.sncj.core.auth_center.service.IRoleService;
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
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService iRoleService;

    @RequestMapping("/page")
    @ResponseBody
    public ReturnMessage<List<RoleEntity>> page(BasePage basePage) {
        Page<RoleEntity> roleEntities = iRoleService.pageRoleByConditions(basePage);
        return ReturnMessage.success((int) roleEntities.getTotalElements(), roleEntities.getContent());
    }
}
