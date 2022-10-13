package ua.lviv.lgs.service.impl;


import org.apache.log4j.Logger;
import ua.lviv.lgs.DAO.UserDao;

import ua.lviv.lgs.DAO.impl.UserDaoImpl;
import ua.lviv.lgs.service.UserService;
import ua.lviv.lgs.domain.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private static Logger LOGGER=Logger.getLogger(UserServiceImpl.class);
    private static UserService userServiceImpl;
    private UserServiceImpl(){
        try {
            userDao=new UserDaoImpl();
        } catch (SQLException | ClassNotFoundException| InstantiationException| IllegalAccessException e) {
            LOGGER.error(e);
        }
    }

    public static UserService getUserService(){
        if(userServiceImpl==null){
            userServiceImpl=new UserServiceImpl();
        }
        return userServiceImpl;
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User read(Integer id) {
        return userDao.read(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    public List<User> readAll() {
        return userDao.readAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
}
