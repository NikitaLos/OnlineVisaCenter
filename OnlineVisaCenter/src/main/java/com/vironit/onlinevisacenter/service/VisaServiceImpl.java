package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.VisaDAO;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import com.vironit.onlinevisacenter.exceptions.service.VisaServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class VisaServiceImpl implements VisaService {

    private VisaDAO visaDAO;

    @Autowired
    public VisaServiceImpl(VisaDAO visaDAO) {
        this.visaDAO = visaDAO;
    }

    @Override
    public void addVisa(Visa visa) throws VisaServiceException, DuplicateException {
        try {
            if(!visaDAO.isDuplicate(visa)){
                visaDAO.save(visa);
            }else {
                throw new DuplicateException("such a visa already exists");
            }
        } catch (EntityFindException | EntitySaveException e) {
            throw new VisaServiceException(e);
        }
    }

    @Override
    public void deleteVisa(Visa visa) throws VisaServiceException {
        try {
            visaDAO.delete(visa);
        } catch (EntityDeleteException e) {
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

    @Transactional
    @Override
    public Visa getVisaEager(Integer id) throws VisaServiceException {
        try {
            Visa visa = visaDAO.find(id);
            visa.getRequiredDocumentTypes().size();
            return visa;
        } catch (EntityFindException e) {
            throw new VisaServiceException(e);
        }
    }
}
