package com.webcrudsecurityboot.repository;

import com.webcrudsecurityboot.model.Role;

import java.util.List;

public interface RoleRepository {
    List<Role> getAllRoles();
    Role show(Long id);
    void save(Role role);
    void update(Role updatedRole);
    void delete(Long id);
}
