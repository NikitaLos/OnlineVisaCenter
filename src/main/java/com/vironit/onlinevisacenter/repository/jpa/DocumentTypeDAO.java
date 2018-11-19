package com.vironit.onlinevisacenter.repository.jpa;

import com.vironit.onlinevisacenter.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentTypeDAO extends JpaRepository<DocumentType,Integer> {
    List<DocumentType> findByName(String documentTypeName);
}
