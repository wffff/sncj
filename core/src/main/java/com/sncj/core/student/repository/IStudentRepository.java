package com.sncj.core.student.repository;

import com.sncj.core.student.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Danny on 2018/7/6.
 */
public interface IStudentRepository extends JpaRepository<StudentEntity, Integer> {
}
