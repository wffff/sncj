package com.sncj.core.student.repository.impl;

import com.sncj.core.baseconfig.BasePage;
import com.sncj.core.baseconfig.utils.RegexUtils;
import com.sncj.core.student.entity.StudentEntity;
import com.sncj.core.student.repository.IStudentRepository;
import com.sncj.core.student.repository.IStudentRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danny on 2018/7/6.
 */
public class IStudentRepositoryImpl implements IStudentRepositoryCustom {
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
    public Page<StudentEntity> findAllStudent(BasePage basePage, StudentEntity student) {
        return iStudentRepository.findAll(condition(student),basePage.getRequestPage());
    }
}
