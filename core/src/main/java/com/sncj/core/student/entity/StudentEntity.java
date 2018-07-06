package com.sncj.core.student.entity;

import com.sncj.core.baseconfig.BaseEntity;

import javax.persistence.*;

/**
 * Created by Danny on 2018/7/6.
 */
@Entity
@Table(name = "t_student")
public class StudentEntity extends BaseEntity {
    private String name;

    public StudentEntity() {
    }

    public StudentEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                ", name='" + name + '\'' +
                '}';
    }
}
