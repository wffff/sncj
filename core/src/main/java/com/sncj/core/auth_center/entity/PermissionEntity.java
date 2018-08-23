package com.sncj.core.auth_center.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sncj.core.baseconfig.BaseEntity;
import com.sncj.core.auth_center.enums.PermissionTypeEnum;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danny on 2018/7/9.
 */
@Entity
@Table(name = "t_permission")
public class PermissionEntity extends BaseEntity {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", updatable = false, insertable = false)
    @Where(clause = "del=false")
    private PermissionEntity parent;
    @Column(name = "pid")
    private Integer pid;
    private PermissionTypeEnum type;
    //权限名称
    private String name;

    //权限描述
    private String description;

    //授权链接
    private String url;

    //请求方法
    private String method;
    private String iconCls;

    private Integer moduleSort;
    private Integer menuSort;
    private Integer tabSort;
    private Integer funcSort;
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "pid", referencedColumnName = "id")
    @Where(clause = "del=false")
    private List<PermissionEntity> children = new ArrayList<>();

    @Transient
    private boolean enabled=false;

    public PermissionEntity() {
    }

    public PermissionEntity(String name, String description, String url, int pid, String method) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.pid = pid;
        this.method = method;
    }
    public PermissionEntity(Integer pid, PermissionTypeEnum type, String name, String url, String iconCls,
                          Integer moduleSort, Integer menuSort, Integer tabSort, Integer funcSort) {
        this.pid = pid;
        this.type = type;
        this.name = name;
        this.url = url;
        this.iconCls = iconCls;
        this.moduleSort = moduleSort;
        this.menuSort = menuSort;
        this.tabSort = tabSort;
        this.funcSort = funcSort;
    }

    public List<PermissionEntity> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionEntity> children) {
        this.children = children;
    }

    public PermissionTypeEnum getType() {
        return type;
    }

    public void setType(PermissionTypeEnum type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public PermissionEntity getParent() {
        return parent;
    }

    public void setParent(PermissionEntity parent) {
        this.parent = parent;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Integer getModuleSort() {
        return moduleSort;
    }

    public void setModuleSort(Integer moduleSort) {
        this.moduleSort = moduleSort;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public Integer getTabSort() {
        return tabSort;
    }

    public void setTabSort(Integer tabSort) {
        this.tabSort = tabSort;
    }

    public Integer getFuncSort() {
        return funcSort;
    }

    public void setFuncSort(Integer funcSort) {
        this.funcSort = funcSort;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
