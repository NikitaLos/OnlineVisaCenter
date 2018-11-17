package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.repository.jpa.VisaDAO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VisaServiceImpl implements VisaService {

    private VisaDAO visaDAO;

    @Autowired
    public VisaServiceImpl(VisaDAO visaDAO) {
        this.visaDAO = visaDAO;
    }

    @Override
    public void addVisa(Visa visa) throws ServiceException {
        checkDuplicate(visa);
        visaDAO.save(visa);
    }

    @Override
    public void updateVisa(Visa visa) throws ServiceException {
        visaDAO.save(visa);
    }

    @Override
    public Visa getVisa(Integer id) throws ServiceException {
        return visaDAO.findById(id).orElseThrow(ServiceException::new);
    }

    @Override
    public void deleteVisaById(Integer id) throws ServiceException {
        visaDAO.deleteById(id);
    }

    @Override
    public List<Visa> getAll() throws ServiceException {
        return visaDAO.findAll();
    }

    @Override
    public List<Visa> getVisasByCountry(Country country) throws ServiceException {
        return visaDAO.findByCountryId(country.getId());
    }

    private void checkDuplicate(Visa visa) throws ServiceException {
        if (!visaDAO.findByTypeAndCountryId(visa.getType(), visa.getCountry().getId()).isEmpty()) {
            throw new ServiceException("Such a visa already exists");
        }
    }
}
