package ua.lviv.lgs.DAO.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.DAO.BucketDao;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.impl.BucketServiceImpl;
import ua.lviv.lgs.utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BucketDaoImpl implements BucketDao {
    private static String READ_ALL="SELECT * FROM bucket";
    private static String CREATE= "insert into bucket (user_id, product_id, purchase_date, subscription, prepayment) values (?,?,?,?,?)";
    private static String READ_BY_ID="select * from bucket where id = ?";
    //private static String UPDATE_BY_ID="update bucket set user_id=?, product_id=?, yearOfPublication=? where idbook = ?";
    private static String DELETE_BY_ID="delete from bucket where id=?";

    private static Logger LOGGER=Logger.getLogger(BucketDaoImpl.class);


    private Connection connection;
    private PreparedStatement preparedStatement;

    public BucketDaoImpl() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        connection= ConnectionUtils.openConnection();

    }

    @Override
    public Bucket create(Bucket bucket) {
        try {
            preparedStatement=connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,bucket.getUserId());
            preparedStatement.setInt(2,bucket.getProductId());
            preparedStatement.setDate(3, new Date(bucket.getPurchaseDate().getTime()));
            int subCastToTinyInt;
            if(!bucket.getSubscription()){
                subCastToTinyInt=0;
                preparedStatement.setInt(4,  subCastToTinyInt);
            } else {
                preparedStatement.setBoolean(4,  true);
            }
            preparedStatement.setDouble(5, bucket.getPrepayment());
            preparedStatement.executeUpdate();
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            resultSet.next();
            bucket.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            LOGGER.error(e);

        }
        return bucket;
    }

    @Override
    public Bucket read(Integer id) {
        Bucket bucket=null;
        try {
            preparedStatement=connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.next();

            Integer BucketId=resultSet.getInt("id");
            Integer userId= resultSet.getInt("user_id");
            Integer productId= resultSet.getInt("product_id");
            java.util.Date purchaseDate=resultSet.getDate("purchase_date");
            Boolean subscription=resultSet.getBoolean("subscription");
            Double prepayment=resultSet.getDouble("prepayment");
            bucket=new Bucket(BucketId,userId,productId,purchaseDate,subscription,prepayment);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        throw new IllegalStateException("There is not update for bucket");
    }

    @Override
    public void delete(Integer id) {
        try {
            preparedStatement=connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Override
    public List<Bucket> readAll() {
        List<Bucket> buckets=new ArrayList<>();
        try {
            preparedStatement=connection.prepareStatement(READ_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer BucketId=resultSet.getInt("id");
                Integer userId= resultSet.getInt("user_id");
                Integer productId= resultSet.getInt("product_id");
                java.util.Date purchaseDate=resultSet.getDate("purchase_date");
                Boolean subscription=resultSet.getBoolean("subscription");
                Double prepayment=resultSet.getDouble("prepayment");
                buckets.add(new Bucket(BucketId,userId,productId,purchaseDate,subscription,prepayment));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return buckets;
    }
}
