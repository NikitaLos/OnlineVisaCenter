package com.vironit.onlinevisacenter.converter.passport;

import com.vironit.onlinevisacenter.dto.passport.PassportRequestDTO;
import com.vironit.onlinevisacenter.entity.Passport;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PassportRequestDTOToPassportConverter implements Converter<PassportRequestDTO, Passport> {
    @Override
    public Passport convert(PassportRequestDTO passportRequestDTO) {
        Passport passport = new Passport();
        BeanUtils.copyProperties(passportRequestDTO,passport);
        return passport;
    }
}
