package com.sncj.core.student.service.impl;

import com.sncj.core.baseconfig.BasePage;
import com.sncj.core.baseconfig.RegexUtils;
import com.sncj.core.student.entity.StudentEntity;
import com.sncj.core.student.repository.IStudentRepository;
import com.sncj.core.student.service.IStudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danny on 2018/7/6.
 */
@Service
public class StudentService implements IStudentService {
    @Resource
    private IStudentRepository iStudentRepository;

    private Specification<StudentEntity> condition(StudentEntity student) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (null != student) {
                if (RegexUtils.notNull(student.getName())) predicates.add(cb.equal(root.get("name"), student.getName()));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    @Override
    public Page<StudentEntity> findAll(BasePage basePage, StudentEntity studentEntity) {
        return iStudentRepository.findAll(condition(studentEntity),basePage.getRequestPage());
    }

    @Override
    public List<StudentEntity> create(StudentEntity student) {
        StudentEntity save = iStudentRepository.save(student);
        return List.of(save);
    }
}
