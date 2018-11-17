package com.vironit.onlinevisacenter.repository.jpa;

import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.exceptions.DAOException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryDAO extends JpaRepository<Country,Integer> {
    List<Country> findByName(String name);
}
