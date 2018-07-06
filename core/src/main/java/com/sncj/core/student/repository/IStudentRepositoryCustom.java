package com.sncj.core.student.repository;

import com.sncj.core.baseconfig.BasePage;
import com.sncj.core.student.entity.StudentEntity;
import org.springframework.data.domain.Page;

/**
 * Created by Danny on 2018/7/6.
 */
public interface IStudentRepositoryCustom {
    Page<StudentEntity> findAllStudent(BasePage basePage, StudentEntity student);
}
