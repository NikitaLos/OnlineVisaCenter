package com.vironit.onlinevisacenter.service.impl;

import com.vironit.onlinevisacenter.converter.visa.VisaRequestDTOToVisaConverter;
import com.vironit.onlinevisacenter.converter.visa.VisaToVisaResponseDTOConverter;
import com.vironit.onlinevisacenter.dto.visa.VisaRequestDTO;
import com.vironit.onlinevisacenter.dto.visa.VisaResponseDTO;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.EntityNotFoundException;
import com.vironit.onlinevisacenter.repository.jpa.VisaDAO;
import com.vironit.onlinevisacenter.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VisaServiceImpl implements VisaService {

    @Autowired
    private VisaDAO visaDAO;

    @Autowired
    private VisaToVisaResponseDTOConverter toVisaResponseDTOConverter;

    @Autowired
    private VisaRequestDTOToVisaConverter toVisaConverter;

    @Override
    public void addVisa(VisaRequestDTO visaRequestDTO) {
        Visa visa = toVisaConverter.convert(visaRequestDTO);
        checkDuplicate(visa);
        visaDAO.save(visa);
    }

    @Override
    public void updateVisa(VisaRequestDTO visaRequestDTO) {
        visaDAO.save(toVisaConverter.convert(visaRequestDTO));
    }

    @Override
    public VisaResponseDTO getVisa(Integer id) {
        return toVisaResponseDTOConverter.convert(visaDAO.findById(id).orElseThrow(() -> new EntityNotFoundException(id)));
    }

    @Override
    public void deleteVisaById(Integer id) {
        visaDAO.deleteById(id);
    }

    @Override
    public List<VisaResponseDTO> getAll() {
        return visaDAO.findAll().stream()
                .map(visa -> toVisaResponseDTOConverter.convert(visa))
                .collect(Collectors.toList());
    }

    @Override
    public List<VisaResponseDTO> getVisasByCountryId(Integer countryId) {
        return visaDAO.findByCountryId(countryId).stream()
                .map((visa -> toVisaResponseDTOConverter.convert(visa)))
                .collect(Collectors.toList());
    }

    private void checkDuplicate(Visa visa) {
        if (!visaDAO.findByTypeAndCountryId(visa.getType(), visa.getCountry().getId()).isEmpty()) {
            throw new DuplicateException("Such a visa already exists");
        }
    }
}
