package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.BaseTest;
import com.vironit.onlinevisacenter.EntityHelper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceTest extends BaseTest {
    @Autowired
    EntityHelper entityHelper;
}
