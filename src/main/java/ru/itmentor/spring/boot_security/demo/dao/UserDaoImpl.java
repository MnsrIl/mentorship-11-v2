package ru.itmentor.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);

        if (user != null) {
            entityManager.remove(user);
            entityManager.flush();
        }
    }

    @Override
    public void deleteUsers() {
        TypedQuery<User> listQuery = entityManager.createQuery("select u from User u", User.class);

        listQuery.getResultList().forEach(entityManager::remove);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByUsername(String username) {
        return entityManager
                .createQuery("select u from User u where u.username =: username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
