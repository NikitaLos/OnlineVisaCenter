package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.dto.request.VisaRequestDTO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.CountryServiceException;
import com.vironit.onlinevisacenter.exceptions.service.DocumentServiceException;
import com.vironit.onlinevisacenter.exceptions.service.VisaServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VisaService {

    @Transactional
    void addVisa(Visa visa) throws VisaServiceException, DuplicateException;

    @Transactional
    void deleteVisa(Visa visa) throws VisaServiceException;

    @Transactional
    void updateVisa(Visa visa) throws VisaServiceException;

    @Transactional
    void deleteVisaById(Integer id) throws VisaServiceException;

    @Transactional
    Visa getVisaEager(Integer visaId) throws VisaServiceException;

    Visa getVisa(Integer id) throws VisaServiceException;

    List<Visa> getAll() throws VisaServiceException;


    List<Visa> getVisasByCountry(Country country) throws VisaServiceException;

    Visa mapDTOToEntity(VisaRequestDTO visaDTO) throws VisaServiceException, CountryServiceException, DocumentServiceException;
}
