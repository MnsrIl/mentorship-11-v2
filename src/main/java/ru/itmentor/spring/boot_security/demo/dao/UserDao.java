package ru.itmentor.spring.boot_security.demo.dao;

import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    void deleteUsers();

    List<User> getUsers();

    User getUserById(Long id);

    User getUserByUsername(String username);
}
