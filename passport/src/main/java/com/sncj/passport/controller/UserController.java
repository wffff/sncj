package com.sncj.passport.controller;

import com.sncj.passport.baseconfig.ReturnMessage;
import com.sncj.passport.entity.PermissionEntity;
import com.sncj.passport.entity.RoleEntity;
import com.sncj.passport.entity.UserEntity;
import com.sncj.passport.service.IPermissionService;
import com.sncj.passport.service.IRoleService;
import com.sncj.passport.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
@Controller
public class UserController {
    @Resource
    private IUserService iUserService;
    @Resource
    private IRoleService iRoleService;
    @Resource
    private IPermissionService iPermissionService;

    @RequestMapping("/")
    public String home(){
        return "index2";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/oauth/login")
    public String oauthLogin(){
        return "oauth/login";
    }

    @RequestMapping("/user2")
    @ResponseBody
    public ReturnMessage<List<UserEntity>> findAll() {
        List<UserEntity> all = iUserService.findAll();
        return ReturnMessage.success(all.size(), all);
    }

    @RequestMapping("/role2")
    @ResponseBody
    public ReturnMessage<List<RoleEntity>> rrr2() {
        List<RoleEntity> all = iRoleService.findAll();
        return ReturnMessage.success(all.size(), all);
    }

    @RequestMapping("/permission2")
    @ResponseBody
    public ReturnMessage<List<PermissionEntity>> permission2() {
        List<PermissionEntity> all = iPermissionService.findAll();
        return ReturnMessage.success(all.size(), all);
    }

    @RequestMapping("/api/permission2")
    @ResponseBody
    public ReturnMessage<List<PermissionEntity>> sepermission2() {
        List<PermissionEntity> all = iPermissionService.findAll();
        return ReturnMessage.success(all.size(), all);
    }
}
