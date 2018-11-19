package com.vironit.onlinevisacenter.converter.visainfo;

import com.vironit.onlinevisacenter.converter.visa.VisaToVisaResponseDTOConverter;
import com.vironit.onlinevisacenter.dto.visainfo.VisaInfoResponseDTO;
import com.vironit.onlinevisacenter.entity.VisaInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VisaInfoToVisaInfoResponseDTOConverter implements Converter<VisaInfo, VisaInfoResponseDTO> {

    @Autowired
    private VisaToVisaResponseDTOConverter toVisaResponseDTOConverter;

    @Override
    public VisaInfoResponseDTO convert(VisaInfo visaInfo) {
        VisaInfoResponseDTO visaInfoResponseDTO = new VisaInfoResponseDTO();
        BeanUtils.copyProperties(visaInfo,visaInfoResponseDTO);
        visaInfoResponseDTO.setVisa(toVisaResponseDTOConverter.convert(visaInfo.getVisa()));
        return visaInfoResponseDTO;
    }
}
