package com.vironit.onlinevisacenter.service.interfaces;

import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VisaService {
    void addVisa(Visa visa) throws ServiceException;
    void updateVisa(Visa visa) throws ServiceException;
    void deleteVisaById(Integer id) throws ServiceException;
    Visa getVisa(Integer id) throws ServiceException;
    List<Visa> getAll() throws ServiceException;
    List<Visa> getVisasByCountry(Country country) throws ServiceException;
}
