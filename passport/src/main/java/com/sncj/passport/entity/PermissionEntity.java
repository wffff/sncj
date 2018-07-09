package com.sncj.passport.entity;

import com.sncj.passport.baseconfig.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Danny on 2018/7/9.
 */
@Entity
@Table(name = "t_permission")
public class PermissionEntity extends BaseEntity {
    //权限名称
    private String name;

    //权限描述
    private String description;

    //授权链接
    private String url;

    //父节点id
    private Integer pid;

    //请求方法
    private String method;

    public PermissionEntity() {
    }

    public PermissionEntity(String name, String description, String url, int pid, String method) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.pid = pid;
        this.method = method;
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

}
