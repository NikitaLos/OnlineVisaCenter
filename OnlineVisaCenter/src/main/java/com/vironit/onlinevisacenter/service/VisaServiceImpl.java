package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.VisaDAO;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.service.inrefaces.VisaService;

public class VisaServiceImpl implements VisaService {

    private VisaDAO visaDAO;

    public VisaServiceImpl(VisaDAO visaDAO) {
        this.visaDAO = visaDAO;
    }

    @Override
    public void addVisa(Visa visa) {
        if(!visaDAO.isDuplicate(visa)){
            visaDAO.create(visa);
        }else {
            //todo exception
        }
    }

    @Override
    public void deleteVisa(Visa visa) {
        visaDAO.delete(visa);
    }

    @Override
    public void updateVisa(Visa visa) {
        visaDAO.update(visa);
    }

    @Override
    public Visa getVisa(Visa visa) {
        return visaDAO.getByPK(visa.getId());
    }
}
