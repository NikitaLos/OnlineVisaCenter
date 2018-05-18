package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.dto.DocumentTypeDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentTypeValidationTest {

    @Autowired
    private  Validator validator;

    @Test
    public void haveNameTest(){
        DocumentTypeDTO documentTypeDTO = new DocumentTypeDTO();
        documentTypeDTO.setName("doc");
        Set<ConstraintViolation<DocumentTypeDTO>> violations = validator.validate(documentTypeDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void invalidNameTest(){
        DocumentTypeDTO documentTypeDTO = new DocumentTypeDTO();
        documentTypeDTO.setName("d");
        Set<ConstraintViolation<DocumentTypeDTO>> violations = validator.validate(documentTypeDTO);
        assertFalse(violations.isEmpty());
    }
}
