package com.sncj.passport.controller;

import com.sncj.passport.entity.OrganizationEntity;
import com.sncj.passport.service.IOrganizationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Danny on 2018/7/13.
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {
    @Resource
    private IOrganizationService iOrganizationService;

    @RequestMapping("/test")
    @ResponseBody
    public List<Integer> test(Integer id, boolean up, boolean self, boolean recursive) {
        return iOrganizationService.listIdsByConditions(id, up, self, recursive);
    }
    @RequestMapping("/test2")
    @ResponseBody
    public List<OrganizationEntity> test2(Integer id) {

        List<Integer> integers = iOrganizationService.listIdsByConditions(id, false, true, true);
       return iOrganizationService.listDevice(integers);
    }
}
