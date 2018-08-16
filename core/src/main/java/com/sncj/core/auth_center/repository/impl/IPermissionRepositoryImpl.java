package com.sncj.core.auth_center.repository.impl;

import com.sncj.core.auth_center.entity.PermissionEntity;
import com.sncj.core.auth_center.enums.PermissionTypeEnum;
import com.sncj.core.auth_center.repository.IPermissionRepository;
import com.sncj.core.auth_center.repository.IPermissionRepositoryCustom;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danny on 2018/8/14.
 */
public class IPermissionRepositoryImpl implements IPermissionRepositoryCustom {
    @Resource
    private IPermissionRepository iPermissionRepository;

    private Specification<PermissionEntity> condition(Boolean pidNull, Integer pid, PermissionTypeEnum permissionType, Boolean del) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (pidNull != null) {
                if (pidNull) {
                    predicates.add(cb.isNull(root.get("pid")));
                } else {
                    predicates.add(cb.isNotNull(root.get("pid")));
                }
            }
            if (pid != null) predicates.add(cb.equal(root.get("pid"), pid));
            if (permissionType != null) predicates.add(cb.equal(root.get("type"), permissionType));
            if (del != null) predicates.add(cb.equal(root.get("del"), del));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    @Override
    public List<PermissionEntity> listByCondition(Boolean pidNull, Integer pid, PermissionTypeEnum permissionType) {
        List<String> list=new ArrayList<>();
        list.add("moduleSort");
        list.add("menuSort");
        list.add("tabSort");
        list.add("funcSort");
        return iPermissionRepository.findAll(condition(pidNull, pid, permissionType, false), new Sort(Sort.Direction.DESC,list));
    }
}
