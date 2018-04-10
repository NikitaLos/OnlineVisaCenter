package com.vironit.onlinevisacenter.dao.JDBCImpl;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.enums.Role;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindExeption;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJDBCDAOImpl extends AbstractJDBCDAO<User> implements UserDAO {

    private String selectQuery = "select * from visa_center.user";
    private String deleteQuery = "delete from visa_center.user where id = ?";
    private String updateQuery = "update visa_center.user set logIn = ?, password = ?, email = ?, role = ? where id = ?";
    private String createQuery = "insert into visa_center.user (logIn,password,email,role) values(?,?,?,?)";
    private String isDuplicateQuery = "select from visa_center.user where logIn = ? or email = ?";
    private String getUserByLoginAndPass = "select from visa_center.user where login = ? or password = ?";


    public UserJDBCDAOImpl() {
        super(ConnectionProvider.getConnection(),User.class);
    }


    @Override
    public User getUserByLoginAndPassword(User user) throws EntityFindExeption {
        try (PreparedStatement statement = connection.prepareStatement(getUserByLoginAndPass)){
            statement.setString(1,user.getLogin());
            statement.setString(2,user.getPassword());
            user = parseResultSet(statement.executeQuery()).iterator().next();
            if(user==null){
                logger.warn("unregistered user");
                throw new EntityFindExeption();
            }
            return user;
        } catch (SQLException e) {
            logger.error("entity find error",e);
            throw new EntityFindExeption(e);
        }
    }


    @Override
    public List<User> parseResultSet(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()){
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("logIn"));
            user.setEmail(rs.getString("email"));
            user.setRole(Role.valueOf(rs.getString("role")));
        }
        return users;
    }

    @Override
    public void prepareStatementForUpdate(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1,user.getLogin());
        statement.setString(2,user.getPassword());
        statement.setString(3,user.getEmail());
        statement.setString(4,user.getRole().toString());
        statement.setInt(5,user.getId());
    }

    @Override
    public void prepareStatementForCreate(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1,user.getLogin());
        statement.setString(2,user.getPassword());
        statement.setString(3,user.getEmail());
        statement.setString(4, user.getRole().toString());
    }

    @Override
    public void prepareStatementForIsDuplicate(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1,user.getLogin());
        statement.setString(2,user.getEmail());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, User user) throws SQLException {
        statement.setInt(1,user.getId());

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
