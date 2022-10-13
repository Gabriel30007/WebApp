package ua.lviv.lgs.service;

import ua.lviv.lgs.shared.AbstractCRUD;
import ua.lviv.lgs.domain.User;

public interface UserService extends AbstractCRUD<User> {
    User getUserByEmail(String email);
}
