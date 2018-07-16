package com.sncj.passport.controller;

import com.sncj.passport.baseconfig.BasePage;
import com.sncj.passport.baseconfig.ReturnMessage;
import com.sncj.passport.entity.RoleEntity;
import com.sncj.passport.service.IRoleService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
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
        return ReturnMessage.success((int)roleEntities.getTotalElements(),roleEntities.getContent());
    }
}
