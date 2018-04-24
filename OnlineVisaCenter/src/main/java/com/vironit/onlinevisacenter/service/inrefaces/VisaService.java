package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.VisaServiceException;

import java.util.List;

public interface VisaService {
    void addVisa(Visa visa) throws VisaServiceException, DuplicateException;
    void deleteVisa(Visa visa) throws VisaServiceException;
    void updateVisa(Visa visa) throws VisaServiceException;
    Visa getVisa(Integer id) throws VisaServiceException;

    void deleteVisaById(Integer id) throws VisaServiceException;

    List<Visa> getAll() throws VisaServiceException;

    Visa getVisaEager(Integer id) throws VisaServiceException;
}
