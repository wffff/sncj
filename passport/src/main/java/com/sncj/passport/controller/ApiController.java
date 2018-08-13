package com.sncj.passport.controller;

import com.sncj.core.baseconfig.ReturnMessage;
import com.sncj.core.baseconfig.utils.SecurityUserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Danny on 2018/8/13.
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    @RequestMapping("/test")
    @ResponseBody
    public ReturnMessage page() {
        System.out.println(SecurityUserUtils.getSecurityUser().getUsername());
        return ReturnMessage.success("成功访问");
    }
}
