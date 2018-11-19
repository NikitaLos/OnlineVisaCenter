package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.dto.clientinfo.ClientInfoRequestDTO;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import org.junit.Test;
import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClientInfoValidationTest extends AbstractValidationTest {

    @Test
    public void validClientInfo(){
        ClientInfoRequestDTO clientInfoRequestDTO = new ClientInfoRequestDTO();
        clientInfoRequestDTO.setAimOfVisit(AimOfVisit.BUSINESS);
        clientInfoRequestDTO.setName("name");
        clientInfoRequestDTO.setSurname("surname");
        clientInfoRequestDTO.setPhotoPath("photo");
        clientInfoRequestDTO.setPhoneNumber("123456");
        clientInfoRequestDTO.setSex("M");
        clientInfoRequestDTO.setDateOfBirth(LocalDate.parse("1995-05-13"));
        Set<ConstraintViolation<ClientInfoRequestDTO>> violations = validator.validate(clientInfoRequestDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void invalidName(){
        ClientInfoRequestDTO clientInfoRequestDTO = new ClientInfoRequestDTO();
        clientInfoRequestDTO.setAimOfVisit(AimOfVisit.BUSINESS);
        clientInfoRequestDTO.setName("n");
        clientInfoRequestDTO.setSurname("surname");
        clientInfoRequestDTO.setPhotoPath("photo");
        clientInfoRequestDTO.setPhoneNumber("123456");
        clientInfoRequestDTO.setSex("M");
        clientInfoRequestDTO.setDateOfBirth(LocalDate.parse("1995-05-13"));
        Set<ConstraintViolation<ClientInfoRequestDTO>> violations = validator.validate(clientInfoRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidSurname(){
        ClientInfoRequestDTO clientInfoRequestDTO = new ClientInfoRequestDTO();
        clientInfoRequestDTO.setAimOfVisit(AimOfVisit.BUSINESS);
        clientInfoRequestDTO.setName("name");
        clientInfoRequestDTO.setPhotoPath("photo");
        clientInfoRequestDTO.setPhoneNumber("123456");
        clientInfoRequestDTO.setSex("M");
        clientInfoRequestDTO.setDateOfBirth(LocalDate.parse("1995-05-13"));
        Set<ConstraintViolation<ClientInfoRequestDTO>> violations = validator.validate(clientInfoRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidPhotoPath(){
        ClientInfoRequestDTO clientInfoRequestDTO = new ClientInfoRequestDTO();
        clientInfoRequestDTO.setAimOfVisit(AimOfVisit.BUSINESS);
        clientInfoRequestDTO.setName("name");
        clientInfoRequestDTO.setSurname("surname");
        clientInfoRequestDTO.setPhoneNumber("123456");
        clientInfoRequestDTO.setSex("M");
        clientInfoRequestDTO.setDateOfBirth(LocalDate.parse("1995-05-13"));
        Set<ConstraintViolation<ClientInfoRequestDTO>> violations = validator.validate(clientInfoRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidPhoneNumber(){
        ClientInfoRequestDTO clientInfoRequestDTO = new ClientInfoRequestDTO();
        clientInfoRequestDTO.setAimOfVisit(AimOfVisit.BUSINESS);
        clientInfoRequestDTO.setName("name");
        clientInfoRequestDTO.setSurname("surname");
        clientInfoRequestDTO.setPhotoPath("photo");
        clientInfoRequestDTO.setPhoneNumber("1234568901010");
        clientInfoRequestDTO.setSex("M");
        clientInfoRequestDTO.setDateOfBirth(LocalDate.parse("1995-05-13"));
        Set<ConstraintViolation<ClientInfoRequestDTO>> violations = validator.validate(clientInfoRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidSex(){
        ClientInfoRequestDTO clientInfoRequestDTO = new ClientInfoRequestDTO();
        clientInfoRequestDTO.setAimOfVisit(AimOfVisit.BUSINESS);
        clientInfoRequestDTO.setName("name");
        clientInfoRequestDTO.setSurname("surname");
        clientInfoRequestDTO.setPhotoPath("photo");
        clientInfoRequestDTO.setPhoneNumber("123456");
        clientInfoRequestDTO.setDateOfBirth(LocalDate.parse("1995-05-13"));
        Set<ConstraintViolation<ClientInfoRequestDTO>> violations = validator.validate(clientInfoRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidAim(){
        ClientInfoRequestDTO clientInfoRequestDTO = new ClientInfoRequestDTO();
        clientInfoRequestDTO.setName("name");
        clientInfoRequestDTO.setSurname("surname");
        clientInfoRequestDTO.setPhotoPath("photo");
        clientInfoRequestDTO.setPhoneNumber("123456");
        clientInfoRequestDTO.setSex("M");
        clientInfoRequestDTO.setDateOfBirth(LocalDate.parse("1995-05-13"));
        Set<ConstraintViolation<ClientInfoRequestDTO>> violations = validator.validate(clientInfoRequestDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidDateOfBirth(){
        ClientInfoRequestDTO clientInfoRequestDTO = new ClientInfoRequestDTO();
        clientInfoRequestDTO.setAimOfVisit(AimOfVisit.BUSINESS);
        clientInfoRequestDTO.setName("name");
        clientInfoRequestDTO.setSurname("surname");
        clientInfoRequestDTO.setPhotoPath("photo");
        clientInfoRequestDTO.setPhoneNumber("123456");
        clientInfoRequestDTO.setSex("M");
        clientInfoRequestDTO.setDateOfBirth(LocalDate.parse("2020-05-13"));
        Set<ConstraintViolation<ClientInfoRequestDTO>> violations = validator.validate(clientInfoRequestDTO,ValidationSequence.class);
        assertFalse(violations.isEmpty());
    }

}
