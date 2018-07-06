package com.sncj.core.student.repository;

import com.sncj.core.baseconfig.BaseRepository;
import com.sncj.core.student.entity.StudentEntity;

/**
 * Created by Danny on 2018/7/6.
 */
public interface IStudentRepository extends BaseRepository<StudentEntity, Integer>,IStudentRepositoryCustom {
}
