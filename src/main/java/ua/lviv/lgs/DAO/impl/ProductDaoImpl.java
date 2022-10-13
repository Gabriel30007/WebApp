package ua.lviv.lgs.DAO.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.DAO.ProductDao;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private static String READ_ALL="SELECT * FROM product";
    private static String CREATE= "insert into product (name, description, price) values (?,?,?)";
    private static String READ_BY_ID="select * from product where id = ?";
    private static String UPDATE_BY_ID="update product set name=?, description=?, price=? where id = ?";
    private static String DELETE_BY_ID="delete from product where id=?";

    private static Logger LOGGER=Logger.getLogger(ProductDaoImpl.class);

    private Connection connection;
    private PreparedStatement preparedStatement;

    public ProductDaoImpl() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        connection= ConnectionUtils.openConnection();

    }

    @Override
    public Product create(Product product) {
        try {
            preparedStatement=connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.executeUpdate();
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return product;
    }

    @Override
    public Product read(Integer id) {
        Product product=null;
        try {
            preparedStatement=connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.next();

            Integer ProductId=resultSet.getInt("id");
            String userId= resultSet.getString("name");
            String productId= resultSet.getString("description");
            Double price=resultSet.getDouble("price");

            product=new Product(ProductId,userId,productId,price);
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return product;
    }

    @Override
    public Product update(Product product) {
        try {
            preparedStatement=connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4,product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return product;
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
    public List<Product> readAll() {
        List<Product> products=new ArrayList<>();
        try {
            preparedStatement=connection.prepareStatement(READ_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer ProductId=resultSet.getInt("id");
                String  name= resultSet.getString("name");
                String  description= resultSet.getString("description");
                Double price=resultSet.getDouble("price");
                products.add(new Product(ProductId,name,description,price));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return products;
    }
}
