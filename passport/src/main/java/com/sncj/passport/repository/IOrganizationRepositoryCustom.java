package com.sncj.passport.repository;

import com.sncj.passport.entity.OrganizationEntity;

import java.util.List;

/**
 * Created by Danny on 2018/7/13.
 */
public interface IOrganizationRepositoryCustom {
    List<OrganizationEntity> listDevice(List<Integer> list);
}
