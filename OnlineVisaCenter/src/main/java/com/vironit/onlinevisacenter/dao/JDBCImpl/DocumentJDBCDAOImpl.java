package com.vironit.onlinevisacenter.dao.JDBCImpl;

import com.vironit.onlinevisacenter.dao.interfaces.DocumentDAO;
import com.vironit.onlinevisacenter.entity.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentJDBCDAOImpl extends AbstractJDBCDAO<Document,Integer> implements DocumentDAO {

    private String selectQuery = "select * from visa_center.document";
    private String deleteQuery = "delete from visa_center.document where id = ?";
    private String updateQuery = "update visa_center.document set name = ? where id = ?";
    private String createQuery = "insert into visa_center.document (name) values(?)";
    private String isDuplicateQuery = "select from visa_center.document where name = ?";


    public DocumentJDBCDAOImpl() {
        super(ConnectionProvider.getConnection());
    }

    @Override
    public List<Document> parseResultSet(ResultSet rs) throws SQLException {
        List<Document> documents = new ArrayList<>();
        while (rs.next()){
            Document document = new Document();
            document.setId(rs.getInt("id"));
            document.setName(rs.getString("name"));
            documents.add(document);
        }
        return documents;
    }

    @Override
    public void prepareStatementForUpdate(PreparedStatement statement, Document document) throws SQLException {
        statement.setString(1,document.getName());
        statement.setInt(2,document.getId());
    }

    @Override
    public void prepareStatementForCreate(PreparedStatement statement, Document document) throws SQLException {
        statement.setString(1,document.getName());

    }

    @Override
    public void prepareStatementForIsDuplicate(PreparedStatement statement, Document document) throws SQLException {
        statement.setString(1,document.getName());
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


}
