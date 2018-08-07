package com.sncj.core.auth_center.repository;


import com.sncj.core.baseconfig.BaseRepository;
import com.sncj.core.auth_center.entity.OrganizationEntity;

/**
 * Created by Danny on 2018/7/13.
 */
public interface IOrganizationRepository extends BaseRepository<OrganizationEntity, Integer>,IOrganizationRepositoryCustom {
}
