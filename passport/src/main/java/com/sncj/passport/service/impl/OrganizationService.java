package com.sncj.passport.service.impl;

import com.sncj.passport.entity.OrganizationEntity;
import com.sncj.passport.repository.IOrganizationNativeSqlRepository;
import com.sncj.passport.repository.IOrganizationRepository;
import com.sncj.passport.service.IOrganizationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
