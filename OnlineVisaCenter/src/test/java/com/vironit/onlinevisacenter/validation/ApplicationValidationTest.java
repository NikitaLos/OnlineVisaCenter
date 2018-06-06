package com.vironit.onlinevisacenter.validation;

import com.vironit.onlinevisacenter.dto.request.ApplicationRequestDTO;
import com.vironit.onlinevisacenter.dto.request.ClientInfoRequestDTO;
import com.vironit.onlinevisacenter.dto.request.PassportRequestDTO;
import com.vironit.onlinevisacenter.dto.request.VisaInfoRequestDTO;
import com.vironit.onlinevisacenter.dto.validation.ValidationSequence;
import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ApplicationValidationTest extends BaseValidationTest {

    @Test
    public void invalidDateOfVisaAndPassportDates(){
        VisaInfoRequestDTO visaInfoRequestDTO = new VisaInfoRequestDTO();
        visaInfoRequestDTO.setVisaId(1);
        visaInfoRequestDTO.setDateFrom(LocalDate.parse("2019-05-16"));
        visaInfoRequestDTO.setDateTo(LocalDate.parse("2020-06-16"));
        visaInfoRequestDTO.setNumOfDaysResidence(15);

        PassportRequestDTO passportRequestDTO = new PassportRequestDTO();
        passportRequestDTO.setCountryOfResidence("USA");
        passportRequestDTO.setNumber("12345678");
        passportRequestDTO.setDateOfEnding(LocalDate.parse("2020-05-31"));
        passportRequestDTO.setDateOfReceiving(LocalDate.parse("2017-05-16"));

        ClientInfoRequestDTO clientInfoRequestDTO = new ClientInfoRequestDTO();
        clientInfoRequestDTO.setAimOfVisit(AimOfVisit.BUSINESS);
        clientInfoRequestDTO.setName("name");
        clientInfoRequestDTO.setSurname("surname");
        clientInfoRequestDTO.setPhotoPath("photo");
        clientInfoRequestDTO.setPhoneNumber("123456");
        clientInfoRequestDTO.setSex("M");
        clientInfoRequestDTO.setDateOfBirth(LocalDate.parse("1995-05-13"));
        clientInfoRequestDTO.setPassport(passportRequestDTO);

        ApplicationRequestDTO applicationRequestDTO = new ApplicationRequestDTO();
        applicationRequestDTO.setClientInfo(clientInfoRequestDTO);
        applicationRequestDTO.setVisaInfo(visaInfoRequestDTO);

        Set<ConstraintViolation<ApplicationRequestDTO>> violations
                = validator.validate(applicationRequestDTO,ValidationSequence.class);
        assertEquals(1,violations.size());
    }

}
