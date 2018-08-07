package com.sncj.core.repository.impl;

import com.sncj.core.baseconfig.IBaseNativeSqlRepository;
import com.sncj.core.dto.OrganizationTreeDTO;
import com.sncj.core.entity.OrganizationEntity;
import com.sncj.core.repository.IOrganizationNativeSqlRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Adam Yao on 2017/12/16.
 */
@Repository
public class OrganizationNativeSqlRepository implements IOrganizationNativeSqlRepository {

    @Resource
    private IBaseNativeSqlRepository iBaseNativeSqlRepository;

    @SuppressWarnings("Duplicates")
    @Override
    public List<Integer> listIdsByConditions(Integer id, boolean up, boolean self, boolean recursive) {
        return iBaseNativeSqlRepository.listTreeRecursive(id, up, self, recursive, OrganizationEntity.class, Integer.class, "id");
    }

    @Override
    public List<OrganizationTreeDTO> listOrganizationTree(Integer rootId) {
        List<OrganizationEntity> resultList = iBaseNativeSqlRepository.listTreeRecursive(rootId, false, true,
                true, OrganizationEntity.class, OrganizationEntity.class, "*");
        return OrganizationTreeDTO.formatTreeDTOByEntity(resultList);
    }

    @Override
    public List<OrganizationTreeDTO> listOrganizationRoot(Integer rootId) {
        List<OrganizationEntity> resultList = iBaseNativeSqlRepository.listTreeRecursive(rootId, false, true,
                false, OrganizationEntity.class, OrganizationEntity.class, "*");
        return OrganizationTreeDTO.formatTreeDTOByEntity(resultList);
    }
}
