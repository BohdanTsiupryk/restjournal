package bts.journal.service;

import bts.journal.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User findByLogin(String login);

    User findByLoginAndPass(String login, String pass);

    boolean checkEmailExist(String email);

    void addUser(User user);

}
