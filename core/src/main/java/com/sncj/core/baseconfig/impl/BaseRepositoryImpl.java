package com.sncj.core.baseconfig.impl;

import com.sncj.core.baseconfig.BaseEntity;
import com.sncj.core.baseconfig.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/**
 * Created by Danny on 2018/7/6.
 */
public class BaseRepositoryImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    private final Class<T> domainClass;
    private EntityManager em;

    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection"})
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.domainClass = domainClass;
        this.em = entityManager;
    }

    @Override
    @Transactional
    public <S extends T> S create(S entity) {
        if (null == entity.getDel()) {
            entity.setDel(false);
        }
        if (null == entity.getTime()) {
            entity.setTime(Date.from(Instant.now()));
        }
        S save = super.save(entity);
        return save;
    }

    @Override
    public Page<T> findAll(Specification spec, Pageable pageable) {
        return super.findAll(spec, pageable);
    }
}
