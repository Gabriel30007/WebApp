package ua.lviv.lgs.DAO;

import ua.lviv.lgs.shared.AbstractCRUD;
import ua.lviv.lgs.domain.User;

public interface UserDao extends AbstractCRUD<User> {
    User getUserByEmail(String email);
}
