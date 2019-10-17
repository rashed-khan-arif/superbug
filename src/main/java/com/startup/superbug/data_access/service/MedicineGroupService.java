package com.startup.superbug.data_access.service;

import com.startup.superbug.data_access.repository.MedicineGroupRepository;
import com.startup.superbug.data_access.repository.MedicineRepository;
import com.startup.superbug.entity.Country;
import com.startup.superbug.entity.Medicine;
import com.startup.superbug.entity.MedicineGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class MedicineGroupService {
    @Autowired
    MedicineGroupRepository repository;
    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    JdbcTemplate dbTemplate;

    @Async("asyncExecutor")
    public CompletableFuture<List<MedicineGroup>> findAll() {
        return CompletableFuture.completedFuture(repository.findAll());
    }

    @Async("asyncExecutor")
    public CompletableFuture<Optional<MedicineGroup>> findById(int groupId) {
        return CompletableFuture.completedFuture(repository.findById(groupId));
    }

    @Async("asyncExecutor")
    public CompletableFuture<List<Medicine>> findMedicineByGroupId(int groupId) {
        List<Medicine> medicines = medicineRepository.findByGroupId(groupId);
        return CompletableFuture.completedFuture(medicines);
    }

    @Async("asyncExecutor")
    public CompletableFuture<MedicineGroup> save(MedicineGroup medicineGroup) {
        MedicineGroup group = repository.save(medicineGroup);
        return CompletableFuture.completedFuture(group);
    }
}
