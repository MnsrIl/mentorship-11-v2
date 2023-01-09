package ru.itmentor.Task11.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itmentor.Task11.model.Role;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }
}
