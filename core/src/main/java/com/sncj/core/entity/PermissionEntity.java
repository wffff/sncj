package com.sncj.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sncj.core.baseconfig.BaseEntity;
import com.sncj.core.enums.PermissionTypeEnum;

import javax.persistence.*;

/**
 * Created by Danny on 2018/7/9.
 */
@Entity
@Table(name = "t_permission")
public class PermissionEntity extends BaseEntity {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", updatable = false, insertable = false)
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

    public PermissionEntity() {
    }

    public PermissionEntity(String name, String description, String url, int pid, String method) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.pid = pid;
        this.method = method;
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
}
