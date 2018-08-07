package com.sncj.core.baseconfig.utils;

import com.sncj.core.entity.UserEntity;
import com.sncj.core.exception.UserException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Adam.yao on 2017/11/10.
 */
public class SecurityUserUtils {

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static UserEntity getSecurityUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                return (UserEntity) principal;
            }
        }
        throw new UserException("登录信息有误");
    }

    public static String getSecurityUserName() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                String principal = (String) authentication.getPrincipal();
                return principal;
            }
        }
        throw new UserException("登录信息有误");
    }
}
