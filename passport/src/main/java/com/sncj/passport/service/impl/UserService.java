package com.sncj.passport.service.impl;

import com.sncj.passport.baseconfig.BasePage;
import com.sncj.passport.baseconfig.utils.RegexUtils;
import com.sncj.passport.entity.PermissionEntity;
import com.sncj.passport.entity.RoleEntity;
import com.sncj.passport.entity.UserEntity;
import com.sncj.passport.exception.UserException;
import com.sncj.passport.repository.IUserRepository;
import com.sncj.passport.service.IUserService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
@Service
public class UserService implements IUserService ,UserDetailsService {
    @Resource
    private IUserRepository iUserRepository;

    @Override
    public Page<UserEntity> pageUserByConditions(BasePage basePage) {
        UserEntity userEntity=new UserEntity();
        userEntity.setDel(false);
        userEntity.setEnabled(true);
        userEntity.setExpired(false);
        userEntity.setLocked(false);
        userEntity.setLimited(false);
        return iUserRepository.findAll(Example.of(userEntity),basePage.getRequestPage());
    }

    @Override
    public List<UserEntity> listUserByConditions() {
        UserEntity userEntity=new UserEntity();
        userEntity.setDel(false);
        userEntity.setEnabled(true);
        userEntity.setExpired(false);
        userEntity.setLocked(false);
        userEntity.setLimited(false);
        return iUserRepository.findAll(Example.of(userEntity));
    }

    @Override
    public List<UserEntity> createUser(String username, String password) {
        if (!RegexUtils.notNull(username)){
            throw new UserException("用户名为空");
        }
        if (!RegexUtils.notNull(password)){
            throw new UserException("密码为空");
        }
        UserEntity userEntity=new UserEntity();
        userEntity.setName(username);
        userEntity.setPassword(password);
        return iUserRepository.saveAll(List.of(userEntity));
    }

    @Override
    public UserEntity findByName(String username) {
        return iUserRepository.findByUsername(username);
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
