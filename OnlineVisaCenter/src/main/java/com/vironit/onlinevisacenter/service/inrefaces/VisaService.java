package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.entity.Visa;

public interface VisaService {
    void addVisa(Visa visa);
    void deleteVisa(Visa visa);
    void updateVisa(Visa visa);
    Visa getVisa(Visa visa);
}
