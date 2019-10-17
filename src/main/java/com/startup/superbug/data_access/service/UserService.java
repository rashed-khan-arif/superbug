package com.startup.superbug.data_access.service;

import com.startup.superbug.data_access.repository.UserRepository;
import com.startup.superbug.data_access.repository.UserRoleRepository;
import com.startup.superbug.entity.User;
import com.startup.superbug.entity.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Async("asyncExecutor")
    public CompletableFuture<Optional<User>> findById(int id) {
        return CompletableFuture.completedFuture(userRepository.findById((long) id));
    }

    @Transactional
    @Async("asyncExecutor")
    public CompletableFuture<User> save(User user) {
        User newUser = userRepository.save(user);
        UserRole role = new UserRole();
        role.setRoleId(1);
        role.setUserId(newUser.getUserId());
        userRoleRepository.save(role);
        return CompletableFuture.completedFuture(newUser);
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<User>> findAll() {
        return CompletableFuture.completedFuture(userRepository.findAll());
    }

    @Async("asyncExecutor")
    public CompletableFuture<Boolean> isExits(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        return CompletableFuture.completedFuture(user.isPresent());
    }

}
