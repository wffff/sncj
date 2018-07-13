package com.sncj.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by Danny on 2018/7/6.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("securedPage")
    @Secured(value = {"ROLE_ADMIN"})
    public String securedPage() {
        return "securedPage";
    }

}
