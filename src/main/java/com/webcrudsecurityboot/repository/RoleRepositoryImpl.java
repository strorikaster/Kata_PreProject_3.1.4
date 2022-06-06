package com.webcrudsecurityboot.repository;

import com.webcrudsecurityboot.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    EntityManager entityManager;


    @SuppressWarnings("unchecked")
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    public Role show(Long id) {
        return entityManager.find(Role.class, id);
    }


    public void save(Role role) {
        entityManager.persist(role);
    }


    public void update(Role updatedRole) {
        entityManager.merge(updatedRole);
    }


    public void delete(Long id) {
        Role role = show(id);
        entityManager.remove(role);
    }
}

