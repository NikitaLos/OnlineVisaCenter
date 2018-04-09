package com.vironit.onlinevisacenter.dao.JDBCImpl;

import com.vironit.onlinevisacenter.dao.interfaces.VisaDAO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.Visa;
import com.vironit.onlinevisacenter.entity.enums.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisaJDBCDAOImpl extends AbstractJDBCDAO<Visa> implements VisaDAO{

    private String selectQuery = "select * from visa_center.visa";
    private String deleteQuery = "delete from visa_center.visa where id = ?";
    private String updateQuery = "update visa_center.visa set type = ?, price = ? where id = ?";
    private String createQuery = "insert into visa_center.visa (type,price) values(?,?)";
    private String isDuplicateQuery = "select from visa_center.visa where type = ?";


    public VisaJDBCDAOImpl() {
        super(ConnectionProvider.getConnection());
    }

    @Override
    public List<Visa> parseResultSet(ResultSet rs) throws SQLException {
        List<Visa> visas = new ArrayList<>();
        while (rs.next()){
            Visa visa = new Visa();
            visa.setId(rs.getInt("id"));
            visa.setType(rs.getString("type"));
            visa.setPrice(rs.getDouble("amount"));
        }
        return visas;
    }

    @Override
    public void prepareStatementForUpdate(PreparedStatement statement, Visa visa) throws SQLException {
        statement.setString(1,visa.getType());
        statement.setDouble(2,visa.getPrice());
        statement.setInt(3,visa.getId());
    }

    @Override
    public void prepareStatementForCreate(PreparedStatement statement, Visa visa) throws SQLException {
        statement.setString(1,visa.getType());
        statement.setDouble(2,visa.getPrice());
    }

    @Override
    public void prepareStatementForIsDuplicate(PreparedStatement statement, Visa visa) throws SQLException {
        statement.setString(1,visa.getType());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Visa visa) throws SQLException {
        statement.setInt(1,visa.getId());

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
