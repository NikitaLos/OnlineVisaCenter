package com.vironit.onlinevisacenter.dao.interfaces;

import com.vironit.onlinevisacenter.entity.Country;
import com.vironit.onlinevisacenter.entity.Document;

import java.util.List;

public interface DocumentDAO {
    void create(Document document);
    void delete(Document document);
    Document getByPK(int key);
    void update(Document document);
    List<Document> getAll();
    boolean isDuplicate(Document document);
}
