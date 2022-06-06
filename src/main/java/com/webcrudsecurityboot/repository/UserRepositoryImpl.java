package com.webcrudsecurityboot.repository;

import com.webcrudsecurityboot.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    public User show(Long id) {
        return entityManager.find(User.class, id);
    }

    public void save(User user) {
        entityManager.persist(user);
        System.out.println("User saved");
    }

    public void update(User updatedUser) {
        entityManager.merge(updatedUser);
        System.out.println("Merge is work");
    }

    public void delete(Long id) {
        User user = show(id);
        entityManager.remove(user);
    }

    public User findByName(String email) {
        return entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.roles roles where u.email = :email ", User.class).setParameter("email", email).getSingleResult();
    }
}
