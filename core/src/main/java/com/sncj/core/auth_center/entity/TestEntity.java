package com.sncj.core.auth_center.entity;

import com.sncj.core.baseconfig.BaseEntity;

import javax.persistence.*;

/**
 * Created by Danny on 2018-08-24.
 */
@Entity
@Table(name = "t_test")
public class TestEntity extends BaseEntity {
    private String name;

    public TestEntity() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
