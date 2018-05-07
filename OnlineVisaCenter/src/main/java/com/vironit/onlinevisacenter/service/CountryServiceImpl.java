package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.CountryDAO;
import com.vironit.onlinevisacenter.dto.CountryDTO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityUpdateException;
import com.vironit.onlinevisacenter.exceptions.service.CountryServiceException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryDAO countryDAO;

    @Autowired
    public CountryServiceImpl(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    @Override
    public void addCountry(Country country) throws CountryServiceException, DuplicateException {
        try {
            if (!countryDAO.isDuplicate(country)){
                countryDAO.save(country);
            }else {
                throw new DuplicateException("Such a country already exists");
            }
        } catch (EntityFindException | EntitySaveException e) {
            throw new CountryServiceException(e);
        }
    }

    @Override
    public void deleteCountry(Country country) throws CountryServiceException {
        try {
            countryDAO.delete(country);
        } catch (EntityDeleteException e) {
            throw new CountryServiceException(e);
        }
    }

    @Override
    public void updateCountry(Country country) throws CountryServiceException {
        try {
            countryDAO.update(country);
        } catch (EntityUpdateException e) {
            throw new CountryServiceException(e);
        }
    }

    @Override
    public Country getCountry(Integer id) throws CountryServiceException {
        try {
            return countryDAO.find(id);
        } catch (EntityFindException e) {
            throw new CountryServiceException(e);
        }
    }

    @Override
    public List<Country> getAll() throws CountryServiceException {
        try {
            return countryDAO.findAll(Country.class);
        } catch (EntityFindException e) {
            throw new CountryServiceException(e);
        }
    }

    @Override
    public void deleteCountryById(Integer countryId) throws UserServiceException {
        try {
            countryDAO.deleteById(countryId);
        } catch (EntityDeleteException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public Country convertToEntity(CountryDTO countryDTO){
        Country country = new Country();
        country.setName(countryDTO.getName());
        country.setId(countryDTO.getId());
        return country;
    }

    @Override
    public CountryDTO convertToDTO(Country country){
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName(country.getName());
        countryDTO.setId(country.getId());
        return countryDTO;
    }

}
