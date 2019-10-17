package com.startup.superbug.data_access.service;

import com.startup.superbug.entity.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CommonService {

    @Autowired
    JdbcTemplate dbTemplate;

    @Async("asyncExecutor")
    public CompletableFuture<List<Country>> getCountryList() {
        String sql = "select * from country";
        List<Country> countries = dbTemplate.query(sql,new BeanPropertyRowMapper<>(Country.class));
        return CompletableFuture.completedFuture(countries);
    }

}
