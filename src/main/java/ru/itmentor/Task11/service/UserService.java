package ru.itmentor.Task11.service;

import ru.itmentor.Task11.model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    void deleteUsers();

    List<User> getUsers();

    User getUserById(Long id);
}
