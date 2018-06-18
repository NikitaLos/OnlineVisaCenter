package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.VisaDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
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
    public void addVisa(Visa visa) throws ServiceException {
        try {
            checkDuplicate(visa);
            visaDAO.save(visa);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateVisa(Visa visa) throws ServiceException {
        try {
            visaDAO.update(visa);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Visa getVisa(Integer id) throws ServiceException {
        try {
            return visaDAO.find(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteVisaById(Integer id) throws ServiceException {
        try {
            visaDAO.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Visa> getAll() throws ServiceException {
        try {
            return visaDAO.findAll(Visa.class);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Visa> getVisasByCountry(Country country) throws ServiceException {
        try {
            return visaDAO.findVisasByCountry(country);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private void checkDuplicate(Visa visa) throws ServiceException, DAOException {
        if (!visaDAO.findVisaByType(visa).isEmpty()){
            throw new ServiceException("Such a visa already exists");
        }
    }
}
