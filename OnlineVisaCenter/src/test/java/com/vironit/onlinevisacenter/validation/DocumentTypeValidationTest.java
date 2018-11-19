package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.dto.documenttype.DocumentTypeDTO;
import org.junit.Test;
import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DocumentTypeValidationTest extends AbstractValidationTest {

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
