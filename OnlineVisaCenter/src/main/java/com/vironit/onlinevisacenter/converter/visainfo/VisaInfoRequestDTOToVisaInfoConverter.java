package com.vironit.onlinevisacenter.converter.visainfo;

import com.vironit.onlinevisacenter.dto.visainfo.VisaInfoRequestDTO;
import com.vironit.onlinevisacenter.entity.VisaInfo;
import com.vironit.onlinevisacenter.exceptions.EntityNotFoundException;
import com.vironit.onlinevisacenter.repository.jpa.VisaDAO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VisaInfoRequestDTOToVisaInfoConverter implements Converter<VisaInfoRequestDTO, VisaInfo> {

    @Autowired
    private VisaDAO visaDAO;

    @Override
    public VisaInfo convert(VisaInfoRequestDTO visaInfoRequestDTO) {
        VisaInfo visaInfo = new VisaInfo();
        BeanUtils.copyProperties(visaInfoRequestDTO, visaInfo);
        visaInfo.setVisa(visaDAO.findById(visaInfoRequestDTO.getVisaId()).orElseThrow(() -> new EntityNotFoundException(visaInfoRequestDTO.getVisaId())));
        return visaInfo;
    }
}
