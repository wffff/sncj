package com.sncj.passport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Created by Danny on 2018/7/9.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/me")
    @ResponseBody
    public Principal user(Principal principal) {
        return principal;
    }
}
