package com.sncj.core.auth_center.service;



import com.sncj.core.auth_center.entity.OrganizationEntity;

import java.util.List;

/**
 * Created by Danny on 2018/7/13.
 */
public interface IOrganizationService {
    /**
     * 获取id列表根据以下条件
     *
     * @param id        当前组织id
     * @param up        向上取id
     * @param self      是否包含当前组织id 默认不包含
     *                  <p></p> Params Examples:
     *                  <p></p> 获取第一层子id列表 : id = foo.id
     *                  <p></p> 获取子层所有id列表 : id = foo.id, recursive = true
     *                  <p></p> 获取父id列表      : id = foo.id, up = true, recursive = true
     * @param recursive 是否递归索取
     * @return
     */
    List<Integer> listIdsByConditions(Integer id, boolean up, boolean self, boolean recursive);

    List<OrganizationEntity> listDevice(List<Integer> integers);
}
