package com.vironit.onlinevisacenter.converter.passport;

import com.vironit.onlinevisacenter.dto.passport.PassportResponseDTO;
import com.vironit.onlinevisacenter.entity.Passport;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PassportToPassportResponseDTOConverter implements Converter<Passport, PassportResponseDTO> {
    @Override
    public PassportResponseDTO convert(Passport passport) {
        PassportResponseDTO passportDTO = new PassportResponseDTO();
        BeanUtils.copyProperties(passport,passportDTO);
        return passportDTO;
    }
}
