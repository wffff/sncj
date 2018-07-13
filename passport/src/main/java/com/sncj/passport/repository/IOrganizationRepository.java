package com.sncj.passport.repository;

import com.sncj.passport.baseconfig.BaseRepository;
import com.sncj.passport.entity.OrganizationEntity;

/**
 * Created by Danny on 2018/7/13.
 */
public interface IOrganizationRepository extends BaseRepository<OrganizationEntity, Integer>,IOrganizationRepositoryCustom {
}
