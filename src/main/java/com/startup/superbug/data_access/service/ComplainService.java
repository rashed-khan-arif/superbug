package com.startup.superbug.data_access.service;

import com.startup.superbug.data_access.repository.ComplainRepository;
import com.startup.superbug.entity.Complain;
import com.startup.superbug.entity.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ComplainService {
    @Autowired
    ComplainRepository complainRepository;

    @Autowired
    JdbcTemplate dbTemplate;

    @Async("asyncExecutor")
    public CompletableFuture<Optional<Complain>> get(int id) {
        return CompletableFuture.completedFuture(complainRepository.findById(id));
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<Complain>> getAll() {
        return CompletableFuture.completedFuture(complainRepository.findAll());
    }

    @Async("asyncExecutor")
    public CompletableFuture<Complain> save(Complain complain) {
        Complain newComplain = complainRepository.save(complain);
        return CompletableFuture.completedFuture(newComplain);
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<Complain>> getAllByUserId(int userId) {
        String sql = "select * from complain where user_id=" + userId;
        List<Complain> complains = dbTemplate.query(sql, new BeanPropertyRowMapper<>(Complain.class));
        return CompletableFuture.completedFuture(complains);
    }
}
