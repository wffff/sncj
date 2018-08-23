package com.sncj.core.auth_center.dto;



import com.sncj.core.baseconfig.utils.TreeUtils;
import com.sncj.core.auth_center.entity.PermissionEntity;
import com.sncj.core.auth_center.enums.PermissionTypeEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Danny on 2018/7/18.
 */
public class PermissionDTO extends TreeUtils.BaseTreeGrid {
    private PermissionTypeEnum type;
    private String parentName;
    private String name;
    private String url;

    @SuppressWarnings("Duplicates")
    public static List<PermissionDTO> formatTreeDTO(Collection<PermissionEntity> list) {
        List<PermissionDTO> dtoList = new ArrayList<>();
        for (PermissionEntity r : list) {
            Integer pid = null;
            PermissionEntity parent = r.getParent();
            if (null != parent) {
                pid = parent.getId();
            }
            Integer sort = r.getMenuSort();
            if (r.getParent() == null) {
                sort = r.getModuleSort();
            } else if (r.getType() == PermissionTypeEnum.TABS) {
                sort = r.getTabSort();
            } else if (r.getType() == PermissionTypeEnum.FUNC) {
                sort = r.getFuncSort();
            }
            String name = "";
            if (r.getParent() != null) {
                name = r.getParent().getName();
            }
            dtoList.add(new PermissionDTO(r.getId(), pid, r.getIconCls(), sort, false, r.getType(), name, r.getName(),
                    r.getUrl()));
        }
        return TreeUtils.formatTree(dtoList, false);
    }

    public static List<PermissionEntity> formatResourceEntity(List<PermissionDTO> list) {
        List<PermissionEntity> reList = new ArrayList<>();
        if (list != null) {
            for (PermissionDTO dto : list) {
                PermissionEntity pidResource = null;
                if (dto.getParentId() != null) {
                    pidResource = new PermissionEntity();
                    pidResource.setId(dto.getParentId());
                }
                PermissionEntity entity = new PermissionEntity(pidResource != null ? pidResource.getId() : null, dto.getType(),
                        dto.getName(),dto.getUrl(),dto.getIcon(),dto.getSort(),dto.getSort(),dto.getSort(),dto.getSort());
                entity.setId(dto.getId());
                reList.add(entity);
                reList.addAll(formatResourceEntity(dto.getChildren()));
            }
        }
        return reList;
    }

    public PermissionDTO(Integer id, Integer parentId, String icon, int sort, boolean spread, PermissionTypeEnum type,
                         String parentName, String name, String url) {
        super(id, parentId, icon, sort, spread);
        this.type = type;
        this.parentName = parentName;
        this.name = name;
        this.url = url;
    }

    public PermissionTypeEnum getType() {
        return type;
    }

    public void setType(PermissionTypeEnum type) {
        this.type = type;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
