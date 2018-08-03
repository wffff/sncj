package com.sncj.passport.baseconfig;

import com.sncj.passport.entity.UserEntity;
import com.sncj.passport.exception.UserException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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
}
