package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.VisaDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import com.vironit.onlinevisacenter.exceptions.service.VisaServiceException;
import com.vironit.onlinevisacenter.service.interfaces.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisaServiceImpl implements VisaService {

    private VisaDAO visaDAO;

    @Autowired
    public VisaServiceImpl(VisaDAO visaDAO) {
        this.visaDAO = visaDAO;
    }

    @Override
    public void addVisa(Visa visa) throws VisaServiceException, DuplicateException {
        try {
            visaDAO.checkDuplicate(visa);
            visaDAO.save(visa);
        } catch (EntityFindException | EntitySaveException e) {
            throw new VisaServiceException(e);
        }
    }

    @Override
    public void updateVisa(Visa visa) throws VisaServiceException {
        try {
            visaDAO.update(visa);
        } catch (EntityUpdateException e) {
            throw new VisaServiceException(e);
        }
    }

    @Override
    public Visa getVisa(Integer id) throws VisaServiceException {
        try {
            return visaDAO.find(id);
        } catch (EntityFindException e) {
            throw new VisaServiceException(e);
        }
    }

    @Override
    public void deleteVisaById(Integer id) throws VisaServiceException {
        try {
            visaDAO.deleteById(id);
        } catch (EntityDeleteException e) {
            throw new VisaServiceException(e);
        }
    }

    @Override
    public List<Visa> getAll() throws VisaServiceException {
        try {
            return visaDAO.findAll(Visa.class);
        } catch (EntityFindException e) {
            throw new VisaServiceException(e);
        }
    }

    @Override
    public List<Visa> getVisasByCountry(Country country) throws VisaServiceException {
        try {
            return visaDAO.findVisasByCountry(country);
        } catch (EntityFindException e) {
            throw new VisaServiceException(e);
        }
    }
}
