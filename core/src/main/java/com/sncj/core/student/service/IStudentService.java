package com.sncj.core.student.service;

import com.sncj.core.baseconfig.BasePage;
import com.sncj.core.student.entity.StudentEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Danny on 2018/7/6.
 */
public interface IStudentService {
    Page<StudentEntity> findAll(BasePage basePage, StudentEntity student);

    List<StudentEntity> create(StudentEntity student);
}
