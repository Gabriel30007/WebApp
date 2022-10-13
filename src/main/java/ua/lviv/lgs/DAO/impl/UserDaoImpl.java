package ua.lviv.lgs.DAO.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.DAO.UserDao;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static String READ_ALL="SELECT * FROM user";
    private static String CREATE= "insert into user (email, first_name, last_name, role,password) values (?,?,?,?,?)";
    private static String READ_BY_ID="select * from user where id = ?";
    private static String READ_BY_EMAIL = "select * from user where email=?";
    private static String UPDATE_BY_ID="update user set email=?, first_name=?, last_name=?, role=?, password=? where id = ?";
    private static String DELETE_BY_ID="delete from user where id=?";

   private static Logger LOGGER=Logger.getLogger(UserDaoImpl.class);

    private Connection connection;
    private PreparedStatement preparedStatement;

    public UserDaoImpl() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        connection= ConnectionUtils.openConnection();
    }
    @Override
    public User create(User user) {
        try {
            preparedStatement=connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public User read(Integer id) {
        User user=null;
        try {
            preparedStatement=connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.next();

            Integer userId=resultSet.getInt("id");
            String email= resultSet.getString("email");
            String first_name= resultSet.getString("first_name");
            String last_name= resultSet.getString("last_name");
            String role=resultSet.getString("role");
            String password=resultSet.getString("password");
            user=new User(userId,email,first_name,last_name,role,password);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public User update(User user) {
        try {
            preparedStatement=connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5,user.getPassword());
            preparedStatement.setInt(6,user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public void delete(Integer id) {
        try {
            preparedStatement=connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<User> readAll() {
        List<User> users=new ArrayList<>();
        try {
            preparedStatement=connection.prepareStatement(READ_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer UserId=resultSet.getInt("id");
                String  email= resultSet.getString("email");
                String  first_name= resultSet.getString("first_name");
                String  last_name= resultSet.getString("last_name");
                String  role= resultSet.getString("role");
                String password=resultSet.getString("password");
                users.add(new User(UserId,email,first_name,last_name,role,password));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return users;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            Integer userId = result.getInt("id");
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");
            String role = result.getString("role");
            String password = result.getString("password");
            user = new User(userId, email, firstName, lastName, role, password);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }
}
