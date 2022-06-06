package com.webcrudsecurityboot.service;

import com.webcrudsecurityboot.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User show(Long id);
    void save(User user);
    void update(User updatedUser);
    void delete(Long id);
    UserDetails loadUserByUsername(String email);
}
