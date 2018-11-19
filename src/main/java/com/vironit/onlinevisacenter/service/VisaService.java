package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dto.visa.VisaRequestDTO;
import com.vironit.onlinevisacenter.dto.visa.VisaResponseDTO;

import java.util.List;

public interface VisaService {
    void addVisa(VisaRequestDTO visaRequestDTO);

    void updateVisa(VisaRequestDTO visaRequestDTO);

    void deleteVisaById(Integer id);

    VisaResponseDTO getVisa(Integer id);

    List<VisaResponseDTO> getAll();

    List<VisaResponseDTO> getVisasByCountryId(Integer countryId);
}
