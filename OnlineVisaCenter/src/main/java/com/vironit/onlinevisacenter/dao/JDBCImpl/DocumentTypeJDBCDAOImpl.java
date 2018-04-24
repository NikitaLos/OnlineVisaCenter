package com.vironit.onlinevisacenter.dao.JDBCImpl;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentTypeDAO;
import com.vironit.onlinevisacenter.entity.DocumentType;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentTypeJDBCDAOImpl extends AbstractJDBCDAO<DocumentType> implements DocumentTypeDAO {

    private String selectQuery = "select * from visa_center.document";
    private String deleteQuery = "delete from visa_center.document where id = ?";
    private String updateQuery = "update visa_center.document set name = ? where id = ?";
    private String createQuery = "insert into visa_center.document (name) values(?)";
    private String isDuplicateQuery = "select from visa_center.document where name = ?";


    public DocumentTypeJDBCDAOImpl() {
        super(ConnectionProvider.getConnection(),DocumentType.class);
    }

    @Override
    public List<DocumentType> parseResultSet(ResultSet rs) throws SQLException {
        List<DocumentType> documentTypes = new ArrayList<>();
        while (rs.next()){
            DocumentType documentType = new DocumentType();
            documentType.setId(rs.getInt("id"));
            documentType.setName(rs.getString("name"));
            documentTypes.add(documentType);
        }
        return documentTypes;
    }

    @Override
    public void prepareStatementForUpdate(PreparedStatement statement, DocumentType documentType) throws SQLException {
        statement.setString(1, documentType.getName());
        statement.setInt(2, documentType.getId());
    }

    @Override
    public void prepareStatementForCreate(PreparedStatement statement, DocumentType documentType) throws SQLException {
        statement.setString(1, documentType.getName());

    }

    @Override
    public void prepareStatementForIsDuplicate(PreparedStatement statement, DocumentType documentType) throws SQLException {
        statement.setString(1, documentType.getName());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, DocumentType documentType) throws SQLException {
        statement.setInt(1,documentType.getId());

    }

    @Override
    public String getSelectQuery() {
        return selectQuery;
    }

    @Override
    public String getCreateQuery() {
        return createQuery;
    }

    @Override
    public String getUpdateQuery() {
        return updateQuery;
    }

    @Override
    public String getDeleteQuery() {
        return deleteQuery;
    }

    @Override
    public String getIsDuplicateQuery() {
        return isDuplicateQuery;
    }


    @Override
    public void deleteById(Integer id) throws EntityDeleteException {

    }
}
