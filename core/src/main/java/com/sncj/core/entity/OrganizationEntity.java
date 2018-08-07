package com.sncj.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sncj.core.baseconfig.BaseEntity;
import com.sncj.core.enums.OrganizationTypeEnum;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Danny on 2018/7/13.
 */
@Entity
@Table(name = "t_organization")
public class OrganizationEntity extends BaseEntity {
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parent_id")
    private OrganizationEntity parent;
    private OrganizationTypeEnum organizationType;
    private String name;
    private String companyName;
    private String description;
    private String memo;

    public OrganizationEntity() {
    }

    public OrganizationEntity(OrganizationEntity parent, OrganizationTypeEnum organizationType, String name, String companyName, String description, String memo) {
        this.parent = parent;
        this.organizationType = organizationType;
        this.name = name;
        this.companyName = companyName;
        this.description = description;
        this.memo = memo;
    }

    public OrganizationEntity getParent() {
        return parent;
    }

    public void setParent(OrganizationEntity parent) {
        this.parent = parent;
    }

    public OrganizationTypeEnum getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(OrganizationTypeEnum organizationType) {
        this.organizationType = organizationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
