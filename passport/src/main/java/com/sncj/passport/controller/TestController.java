package com.sncj.passport.controller;

import com.sncj.core.auth_center.entity.TestEntity;
import com.sncj.core.baseconfig.BasePage;
import com.sncj.core.baseconfig.ReturnMessage;
import com.sncj.core.test.service.ITestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("test")
public class TestController {
    @Resource
    private ITestService iTestService;
    @ResponseBody
    @RequestMapping("test")
    public ReturnMessage<List> test(BasePage basePage) {
        List all = iTestService.findAll(basePage);
        return ReturnMessage.success(all.size(),all);
    }
}
