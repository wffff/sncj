package com.sncj.passport.service.impl;

import com.sncj.passport.entity.RoleEntity;
import com.sncj.passport.repository.IRoleRepository;
import com.sncj.passport.repository.IUserRepository;
import com.sncj.passport.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
@Service
public class RoleService implements IRoleService {
    @Resource
    private IRoleRepository iRoleRepository;

    @Override
    public List<RoleEntity> findAll() {
        return iRoleRepository.findAll();
    }
}
