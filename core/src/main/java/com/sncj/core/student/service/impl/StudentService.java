package com.sncj.core.student.service.impl;

import com.sncj.core.baseconfig.BasePage;
import com.sncj.core.student.entity.StudentEntity;
import com.sncj.core.student.repository.IStudentRepository;
import com.sncj.core.student.service.IStudentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Danny on 2018/7/6.
 */
@Service
public class StudentService implements IStudentService {
    @Resource
    private IStudentRepository iStudentRepository;

    @Override
    public Page<StudentEntity> findAll(BasePage basePage, StudentEntity studentEntity) {
        return iStudentRepository.findAll(basePage.getRequestPage());
    }
}
