package com.sncj.passport.controller;

import com.sncj.core.auth_center.entity.UserEntity;
import com.sncj.core.auth_center.service.IUserService;
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
import java.util.Set;

/**
 * Created by Danny on 2018/7/9.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService iRoleService;
    @Resource
    private IUserService iUserService;

    @RequestMapping("/page")
    @ResponseBody
    public ReturnMessage<List<RoleEntity>> page(BasePage basePage) {
        Page<RoleEntity> roleEntities = iRoleService.pageRoleByConditions(basePage);
        return ReturnMessage.success((int) roleEntities.getTotalElements(), roleEntities.getContent());
    }

    @RequestMapping("getByUserId")
    @ResponseBody
    public ReturnMessage<List<RoleEntity>> getByUserId(Integer id) {
        UserEntity u = iUserService.findById(id);
        Set<RoleEntity> has = u.getRole();
        List<RoleEntity> all = iRoleService.findAll();
        for (RoleEntity r:all){
            if (has.contains(r)){
                r.setEnabled(true);
            }
        }
        return ReturnMessage.success(all.size(), all);
    }

    @RequestMapping("grant")
    @ResponseBody
    public ReturnMessage grant(Integer userId, Integer roleId, boolean enabled) {
        ReturnMessage result;
        try {
            UserEntity u = iUserService.enabled(userId, roleId, enabled);
            result = ReturnMessage.success(1,u);
        } catch (Exception e) {
            result = ReturnMessage.failed(e.getMessage());
        }
        return result;
    }
}
