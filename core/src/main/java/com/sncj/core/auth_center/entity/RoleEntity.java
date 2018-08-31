package com.sncj.core.auth_center.entity;

import com.sncj.core.baseconfig.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Danny on 2018/7/9.
 */
@Entity
@Table(name = "t_role")
public class RoleEntity extends BaseEntity {

    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_role_permission",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")})
    @OrderBy(value = "id DESC")
    @Where(clause = "del = false")
    private Set<PermissionEntity> permission = new HashSet<>();

    @Transient
    private boolean enabled=false;

    public RoleEntity() {
    }

    public RoleEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public RoleEntity(Integer id, String name, String description) {
        this.id=id;
        this.name=name;
        this.description=description;
    }

    public Set<PermissionEntity> getPermission() {
        return permission;
    }

    public void setPermission(Set<PermissionEntity> permission) {
        this.permission = permission;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
