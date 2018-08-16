package com.sncj.core.auth_center.repository;


import com.sncj.core.auth_center.entity.PermissionEntity;
import com.sncj.core.auth_center.enums.PermissionTypeEnum;

import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
public interface IPermissionRepositoryCustom {
    List<PermissionEntity> listByCondition(Boolean pidNull,Integer pid,PermissionTypeEnum permissionType);
}
