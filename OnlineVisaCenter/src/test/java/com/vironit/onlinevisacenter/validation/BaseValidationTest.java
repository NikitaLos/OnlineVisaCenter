package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

public abstract class BaseValidationTest extends BaseTest {
    @Autowired
    Validator validator;
}
