package com.vironit.onlinevisacenter.service.inrefaces;

import com.vironit.onlinevisacenter.entity.Country;

public interface CountryService {
    void addCountry(Country country);
    void deleteCountry(Country country);
    void updateCountry(Country country);
    void getCountry(Country country);
}
