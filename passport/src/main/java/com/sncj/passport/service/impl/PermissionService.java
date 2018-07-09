package com.sncj.passport.service.impl;

import com.sncj.passport.entity.PermissionEntity;
import com.sncj.passport.entity.RoleEntity;
import com.sncj.passport.repository.IPermissionRepository;
import com.sncj.passport.repository.IRoleRepository;
import com.sncj.passport.service.IPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
@Service
public class PermissionService implements IPermissionService {
    @Resource
    private IPermissionRepository iPermissionRepository;

    @Override
    public List<PermissionEntity> findAll() {
        return iPermissionRepository.findAll();
    }
}
