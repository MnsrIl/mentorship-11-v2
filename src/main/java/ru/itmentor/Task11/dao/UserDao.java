package ru.itmentor.Task11.dao;

import ru.itmentor.Task11.model.User;

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
