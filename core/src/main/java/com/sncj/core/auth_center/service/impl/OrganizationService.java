package com.sncj.core.auth_center.service.impl;

import com.sncj.core.auth_center.entity.OrganizationEntity;
import com.sncj.core.auth_center.repository.IOrganizationNativeSqlRepository;
import com.sncj.core.auth_center.repository.IOrganizationRepository;
import com.sncj.core.auth_center.service.IOrganizationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Danny on 2018/7/13.
 */
@Service
public class OrganizationService implements IOrganizationService {
    @Resource
    private IOrganizationNativeSqlRepository iOrganizationNativeSqlRepository;
    @Resource
    private IOrganizationRepository iOrganizationRepository;

    @Override
    public List<Integer> listIdsByConditions(Integer id, boolean up, boolean self, boolean recursive) {
        return iOrganizationNativeSqlRepository.listIdsByConditions(id, up, self, recursive);
    }

    @Override
    public List<OrganizationEntity> listDevice(List<Integer> integers) {
        return iOrganizationRepository.listDevice(integers);
    }
}
