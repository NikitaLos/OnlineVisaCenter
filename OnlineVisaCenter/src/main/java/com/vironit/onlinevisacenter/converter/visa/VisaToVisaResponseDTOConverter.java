package com.vironit.onlinevisacenter.converter.visa;

import com.vironit.onlinevisacenter.converter.country.CountryToCountryDTOConverter;
import com.vironit.onlinevisacenter.converter.documenttype.DocumentTypeToDocumentTypeDTOConverter;
import com.vironit.onlinevisacenter.dto.documenttype.DocumentTypeDTO;
import com.vironit.onlinevisacenter.dto.visa.VisaResponseDTO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.entity.Visa;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VisaToVisaResponseDTOConverter implements Converter<Visa, VisaResponseDTO> {

    @Autowired
    private CountryToCountryDTOConverter toCountryDTOConverter;

    @Autowired
    private DocumentTypeToDocumentTypeDTOConverter toDocumentTypeDTOConverter;

    @Override
    public VisaResponseDTO convert(Visa visa) {
        VisaResponseDTO visaResponseDTO = new VisaResponseDTO();
        BeanUtils.copyProperties(visa, visaResponseDTO);
        visaResponseDTO.setCountry(toCountryDTOConverter.convert(visa.getCountry()));
        List<DocumentTypeDTO> documentTypesDTO = new ArrayList<>();
        for (DocumentType documentType : visa.getRequiredDocumentTypes()) {
            documentTypesDTO.add(toDocumentTypeDTOConverter.convert(documentType));
        }
        visaResponseDTO.setRequiredDocumentTypes(documentTypesDTO);
        return visaResponseDTO;
    }
}
