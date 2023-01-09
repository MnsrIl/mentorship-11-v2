package ru.itmentor.Task11.service;

import org.springframework.stereotype.Service;
import ru.itmentor.Task11.dao.RoleDao;
import ru.itmentor.Task11.model.Role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Set<Role> findRolesByIDs(List<Long> rolesIDs) {
        return roleRepository
                .getAllRoles()
                .stream()
                .filter(role -> rolesIDs.contains(role.getId()))
                .collect(Collectors.toSet());
    }
}
