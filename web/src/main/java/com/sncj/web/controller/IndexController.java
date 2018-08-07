package com.sncj.web.controller;

import com.sncj.core.baseconfig.utils.SecurityUserUtils;
import com.sncj.core.auth_center.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Danny on 2018/7/6.
 */
@Controller
public class IndexController {
    @Resource
    private IUserService iUserService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("securedPage")
//    @Secured(value = {"ROLE_ADMIN"})
    public String securedPage() {
        System.out.println(SecurityUserUtils.getSecurityUserName());
        return "securedPage";
    }

}
