package ua.lviv.lgs.service.impl;


import org.apache.log4j.Logger;
import ua.lviv.lgs.DAO.ProductDao;

import ua.lviv.lgs.DAO.impl.ProductDaoImpl;
import ua.lviv.lgs.service.ProductService;
import ua.lviv.lgs.domain.Product;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    private static Logger LOGGER=Logger.getLogger(ProductServiceImpl.class);
    private static ProductService productServiceImpl;
    private ProductServiceImpl(){
        try {
            productDao=new ProductDaoImpl();
        } catch (SQLException | ClassNotFoundException| InstantiationException| IllegalAccessException e) {
            LOGGER.error(e);
        }
    }
    public static ProductService getProductService(){
        if(productServiceImpl==null){
            productServiceImpl=new ProductServiceImpl();
        }
        return productServiceImpl;
    }
    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product read(Integer id) {
        return productDao.read(id);
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public void delete(Integer id) {
        productDao.delete(id);
    }

    @Override
    public List<Product> readAll() {
        return productDao.readAll();
    }
    @Override
    public Map<Integer, Product> readAllMap() {
        return  readAll().stream().collect(Collectors.toMap(Product::getId, Function.identity()));
    }
}
