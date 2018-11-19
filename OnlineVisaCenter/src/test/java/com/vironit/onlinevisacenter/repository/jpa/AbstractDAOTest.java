package com.vironit.onlinevisacenter.repository.jpa;

import com.vironit.onlinevisacenter.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public abstract class AbstractDAOTest extends AbstractTest {
    @Autowired
    JpaRepositoryTestData jpaRepositoryTestData;

    @PersistenceContext
    EntityManager entityManager;
}
