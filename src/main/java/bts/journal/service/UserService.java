package bts.journal.service;

import bts.journal.model.User;

public interface UserService {

    User findByLogin(String login);

    User findByLoginAndPass(String login, String pass);

    boolean checkEmailExist(String email);

    void addUser(User user);

}
