package com.vironit.onlinevisacenter.service.impl;

import com.vironit.onlinevisacenter.converter.country.CountryDTOToCountryConverter;
import com.vironit.onlinevisacenter.converter.country.CountryToCountryDTOConverter;
import com.vironit.onlinevisacenter.dto.country.CountryDTO;
import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.EntityNotFoundException;
import com.vironit.onlinevisacenter.repository.jpa.CountryDAO;
import com.vironit.onlinevisacenter.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDAO countryDAO;

    @Autowired
    private CountryToCountryDTOConverter toCountryDTOConverter;

    @Autowired
    private CountryDTOToCountryConverter toCountryConverter;

    @Override
    public void addCountry(CountryDTO countryDTO) {
        Country country = toCountryConverter.convert(countryDTO);
        checkDuplicate(country);
        countryDAO.save(country);
    }

    @Override
    public void updateCountry(CountryDTO countryDTO) {
        countryDAO.save(toCountryConverter.convert(countryDTO));
    }

    @Override
    public CountryDTO getCountry(Integer id) {
        return toCountryDTOConverter.convert(countryDAO.findById(id).orElseThrow(() -> new EntityNotFoundException(id)));
    }

    @Override
    public List<CountryDTO> getAll() {
        return countryDAO.findAll().stream()
                .map((country -> toCountryDTOConverter.convert(country)))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCountryById(Integer countryId) {
        countryDAO.deleteById(countryId);

    }

    private void checkDuplicate(Country country) {
        if (!countryDAO.findByName(country.getName()).isEmpty()) {
            throw new DuplicateException("Such a country already exists");
        }
    }

}
