package com.sncj.core.auth_center.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sncj.core.baseconfig.BaseEntity;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Danny on 2018/7/9.
 */
@Entity
@Table(name = "t_user")
public class UserEntity extends BaseEntity implements UserDetails {
    private Integer organizationId;
    private String username;
    @JsonIgnore
    private String password;
    private String mobile;
    private String email;
    private String name;
    private Boolean enabled;
    private Boolean expired;
    private Boolean locked;
    private Boolean limited;
    @Transient
    @JSONField(serialize = false)
    private Set<GrantedAuthority> authorities;
    //    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    @OrderBy(value = "id DESC")
    @Where(clause = "del = false")
    private Set<RoleEntity> role = new HashSet<>();

    public UserEntity() {

    }

    public UserEntity(Integer id, String username, String password, String mobile, String email, String name, boolean enabled, boolean expired, boolean locked, boolean limited, Date time, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
        this.name = name;
        this.enabled = enabled;
        this.expired = expired;
        this.locked = locked;
        this.limited = limited;
        this.time = time;
        this.authorities = (Set<GrantedAuthority>) authorities;
    }

    public UserEntity(Integer id, String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this(id, username, password, enabled, false, false, false, authorities);
    }

    public UserEntity(Integer id, String username, String password, boolean enabled, boolean expired, boolean locked, boolean limited, Collection<? extends GrantedAuthority> authorities) {
        this(id, username, password, null, null, null, enabled, expired, locked, limited, null, authorities);
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<RoleEntity> getRole() {
        return role;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getLimited() {
        return limited;
    }

    public void setLimited(Boolean limited) {
        this.limited = limited;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setRole(Set<RoleEntity> role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !limited;
    }

    public Date getTime() {
        return time;
    }

}
