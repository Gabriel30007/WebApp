package ua.lviv.lgs.service.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.DAO.BucketDao;
import ua.lviv.lgs.DAO.impl.BucketDaoImpl;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.domain.Bucket;


import java.sql.SQLException;
import java.util.List;

public class BucketServiceImpl implements BucketService {
    private BucketDao bucketDao;
    private static Logger LOGGER=Logger.getLogger(BucketServiceImpl.class);
    private static BucketService bucketServiceImpl;
    private BucketServiceImpl(){
        try {
            bucketDao=new BucketDaoImpl();
        } catch (SQLException | ClassNotFoundException| InstantiationException| IllegalAccessException e) {
            LOGGER.error(e);
        }
    }
    public static BucketService getBucketService(){
        if(bucketServiceImpl==null){
            bucketServiceImpl=new BucketServiceImpl();
        }
        return bucketServiceImpl;
    }

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket read(Integer id) {
        return bucketDao.read(id);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public void delete(Integer id) {
        bucketDao.delete(id);
    }

    @Override
    public List<Bucket> readAll() {
        return bucketDao.readAll();
    }
}
