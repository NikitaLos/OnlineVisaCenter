package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

public abstract class AbstractValidationTest extends AbstractTest {
    @Autowired
    Validator validator;
}
