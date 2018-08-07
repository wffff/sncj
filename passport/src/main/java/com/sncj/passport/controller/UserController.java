package com.sncj.passport.controller;

import com.sncj.core.baseconfig.BasePage;
import com.sncj.core.baseconfig.ReturnMessage;
import com.sncj.core.entity.UserEntity;
import com.sncj.core.service.IUserService;
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
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService iUserService;

    @RequestMapping("/")
    public String home() {
        return "/user/index";
    }

    @RequestMapping("/page")
    @ResponseBody
    public ReturnMessage<List<UserEntity>> page(BasePage basePage) {
        Page<UserEntity> userEntities = iUserService.pageUserByConditions(basePage);
        return ReturnMessage.success((int) userEntities.getTotalElements(), userEntities.getContent());
    }
}
