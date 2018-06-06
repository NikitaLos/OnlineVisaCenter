package com.vironit.onlinevisacenter.dao;

import com.vironit.onlinevisacenter.BaseTest;
import com.vironit.onlinevisacenter.EntityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public abstract class BaseDAOTest extends BaseTest {
    @Autowired
    EntityHelper entityHelper;

    @PersistenceContext
    EntityManager entityManager;
}
