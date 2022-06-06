package com.webcrudsecurityboot.service;

import com.webcrudsecurityboot.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role show(Long id);
    void save(Role role);
}
