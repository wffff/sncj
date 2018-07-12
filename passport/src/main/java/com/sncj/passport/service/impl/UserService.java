package com.sncj.passport.service.impl;

import com.sncj.passport.baseconfig.MyGrantedAuthority;
import com.sncj.passport.baseconfig.utils.RegexUtils;
import com.sncj.passport.entity.PermissionEntity;
import com.sncj.passport.entity.RoleEntity;
import com.sncj.passport.entity.UserEntity;
import com.sncj.passport.repository.IUserRepository;
import com.sncj.passport.service.IUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Danny on 2018/7/9.
 */
@Service
public class UserService implements IUserService ,UserDetailsService {
    @Resource
    private IUserRepository iUserRepository;
    @Override
    public List<UserEntity> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = iUserRepository.findByUsername(username);
        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (RoleEntity role:user.getRole()){
                for (PermissionEntity permission:role.getPermission()){
                    if (permission != null && permission.getName() != null) {
                        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                        //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                        grantedAuthorities.add(grantedAuthority);
                    }
                }
            }
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}
