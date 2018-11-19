package com.vironit.onlinevisacenter.repository.jpa;


import com.vironit.onlinevisacenter.entity.Visa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisaDAO extends JpaRepository<Visa,Integer> {
    List<Visa> findByTypeAndCountryId(String type, Integer countryId);
    List<Visa> findByCountryId(Integer countryId);
}
