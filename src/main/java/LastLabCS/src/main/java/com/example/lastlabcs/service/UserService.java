package com.example.lastlabcs.service;

import com.example.lastlabcs.entity.Role;
import com.example.lastlabcs.entity.User;
import com.example.lastlabcs.repository.RoleRepository;
import com.example.lastlabcs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUserName(username);
        Role role = roleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
    }

    public User registerNewUser(User user) {
        Role role = roleRepository.findByRoleName("USER");
        user.getRoles().add(role);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return userRepository.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
