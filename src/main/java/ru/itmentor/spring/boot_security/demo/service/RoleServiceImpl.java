package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.dao.RoleDao;
import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleRepository;

    public RoleServiceImpl(RoleDao roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.getAllRoles();
    }

    @Override
    public Role[] findRolesByIDs(List<Long> rolesIDs) {
        return roleRepository
                .getAllRoles()
                .stream()
                .filter(role -> rolesIDs.contains(role.getId()))
                .toArray(Role[]::new);
    }
}
