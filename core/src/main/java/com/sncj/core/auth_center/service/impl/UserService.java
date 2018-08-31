package com.sncj.core.auth_center.service.impl;

import com.sncj.core.auth_center.entity.RoleEntity;
import com.sncj.core.auth_center.entity.UserEntity;
import com.sncj.core.auth_center.exception.UserException;
import com.sncj.core.auth_center.repository.IUserRepository;
import com.sncj.core.auth_center.service.IRoleService;
import com.sncj.core.auth_center.service.IUserService;
import com.sncj.core.baseconfig.BasePage;
import com.sncj.core.baseconfig.utils.RegexUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Danny on 2018/7/9.
 */
@Service
public class UserService implements IUserService, UserDetailsService {
    @Resource
    private IUserRepository iUserRepository;
    @Resource
    private IRoleService iRoleService;

    @Override
    public Page<UserEntity> pageUserByConditions(BasePage basePage, String name) {
        UserEntity userEntity = new UserEntity();
        if (RegexUtils.notNull(name)) {
            userEntity.setName(name);
        }
        userEntity.setDel(false);
        userEntity.setEnabled(true);
        userEntity.setExpired(false);
        userEntity.setLocked(false);
        userEntity.setLimited(false);
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<UserEntity> of = Example.of(userEntity, matcher);
        return iUserRepository.findAll(of, basePage.getRequestPage());
    }

    @Override
    public List<UserEntity> listUserByConditions() {
        UserEntity userEntity = new UserEntity();
        userEntity.setDel(false);
        userEntity.setEnabled(true);
        userEntity.setExpired(false);
        userEntity.setLocked(false);
        userEntity.setLimited(false);
        return iUserRepository.findAll(Example.of(userEntity));
    }

    @Override
    public List<UserEntity> createUser(String username, String password) {
        if (!RegexUtils.notNull(username)) {
            throw new UserException("用户名为空");
        }
        if (!RegexUtils.notNull(password)) {
            throw new UserException("密码为空");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(username);
        userEntity.setPassword(password);
        return iUserRepository.saveAll(List.of(userEntity));
    }

    @Override
    public UserEntity findByName(String username) {
        return iUserRepository.findByUsername(username);
    }

    @Override
    public UserEntity add(String username, String password, String name) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setUsername(username);
        userEntity.setPassword(new BCryptPasswordEncoder().encode(password));
        return iUserRepository.save(userEntity);
    }

    @Override
    public UserEntity enabled(Integer userId, Integer roleId, boolean enabled) {
        UserEntity u = iUserRepository.findOne(userId);
        RoleEntity r = iRoleService.findById(roleId);
        if (enabled) {
            u.getRole().add(r);
        } else {
            u.getRole().remove(r);
        }
        return iUserRepository.update(u);
    }

    @Override
    public UserEntity findById(Integer id) {
        return iUserRepository.findOne(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = iUserRepository.findByUsername(username);
        if (user != null) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (RoleEntity role : user.getRole()) {
                if (role != null && role.getName() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new UserEntity(user.getId(), user.getUsername(), user.getPassword(), user.getEnabled(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}
