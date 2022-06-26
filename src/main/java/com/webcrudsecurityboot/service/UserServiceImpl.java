package com.webcrudsecurityboot.service;

import com.webcrudsecurityboot.model.Role;
import com.webcrudsecurityboot.repository.UserRepository;
import com.webcrudsecurityboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User show(Long id) {
        return userRepository.show(id);
    }

    @Override
    @Transactional
    public void save(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles().size() == 0) {
            user.addRole(roleService.show(2L));
        }
        roleService.save(roleService.show(2L));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User updatedUser) {
        if(!updatedUser.getPassword().equals(userRepository.show(updatedUser.getId()).getPassword())) {
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        if(!updatedUser.getRoles().equals(userRepository.show(updatedUser.getId()).getRoles())) {
            updatedUser.setRoles(updatedUser.getRoles());
        }

        for(Role role : updatedUser.getRoles()) {
            roleService.save(role);
        }

//        if(!updatedUser.getName().equals(userRepository.show(updatedUser.getId()).getName())) {
//            updatedUser.setName(updatedUser.getName());
//        }
//
//        if(!updatedUser.getSurName().equals(userRepository.show(updatedUser.getId()).getSurName())) {
//            updatedUser.setSurName(updatedUser.getSurName());
//        }
//
//        if(updatedUser.getAge() != (userRepository.show(updatedUser.getId()).getAge())) {
//            updatedUser.setAge(updatedUser.getAge());
//        }
//
//        if(!updatedUser.getEmail().equals (userRepository.show(updatedUser.getId()).getEmail())) {
//            updatedUser.setEmail(updatedUser.getEmail());
//        }
       return userRepository.update(updatedUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByName(email);
        if(user == null) {
            throw new UsernameNotFoundException("User " + email + " not found!");

        }
        return user;
    }


}
