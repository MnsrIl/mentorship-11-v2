package ru.itmentor.Task11.service;

import ru.itmentor.Task11.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> findAllRoles();

    Set<Role> findRolesByIDs(List<Long> rolesIDs);
}
