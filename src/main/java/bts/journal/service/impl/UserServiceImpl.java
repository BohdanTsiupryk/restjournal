package bts.journal.service.impl;

import bts.journal.model.User;
import bts.journal.repo.UserRepo;
import bts.journal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User findByLogin(String login) {
        return userRepo.findByEmail(login)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User findByLoginAndPass(String login, String pass) {

        User user = userRepo.findByEmail(login)
                .orElseThrow(NoSuchElementException::new);

        if (passwordEncoder.matches(pass, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean checkEmailExist(String email) {
        return userRepo.findByEmail(email).isPresent();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userRepo.save(user);
    }
}
