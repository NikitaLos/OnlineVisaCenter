package com.vironit.onlinevisacenter.converter.visa;

import com.vironit.onlinevisacenter.dto.visa.VisaRequestDTO;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.EntityNotFoundException;
import com.vironit.onlinevisacenter.repository.jpa.CountryDAO;
import com.vironit.onlinevisacenter.repository.jpa.DocumentTypeDAO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VisaRequestDTOToVisaConverter implements Converter<VisaRequestDTO, Visa> {

    @Autowired
    private CountryDAO countryDAO;

    @Autowired
    private DocumentTypeDAO documentTypeDAO;

    @Override
    public Visa convert(VisaRequestDTO visaRequestDTO) {
        Visa visa = new Visa();
        BeanUtils.copyProperties(visaRequestDTO, visa);
        visa.setCountry(countryDAO.findById(visaRequestDTO.getCountryId()).orElseThrow(() -> new EntityNotFoundException(visaRequestDTO.getCountryId())));
        if (visaRequestDTO.getRequiredDocumentTypesId() != null) {
            for (int id : visaRequestDTO.getRequiredDocumentTypesId()) {
                visa.getRequiredDocumentTypes().add(documentTypeDAO.findById(id).orElseThrow(() -> new EntityNotFoundException(id)));
            }
        }
        return visa;
    }
}
