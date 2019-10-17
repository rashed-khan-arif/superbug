package com.startup.superbug.data_access.service;

import com.startup.superbug.data_access.repository.MedicineRepository;
import com.startup.superbug.entity.Medicine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class MedicineService {
    @Autowired
    MedicineRepository medicineRepository;

    @Async("asyncExecutor")
    public CompletableFuture<List<Medicine>> findAll() {
        return CompletableFuture.completedFuture(medicineRepository.findAll());
    }

    @Async("asyncExecutor")
    public CompletableFuture<Medicine> save(Medicine medicine) {
        return CompletableFuture.completedFuture(medicineRepository.save(medicine));
    }

    @Async("asyncExecutor")
    public CompletableFuture<Optional<Medicine>> findById(int medicineId) {
        return CompletableFuture.completedFuture(medicineRepository.findById(medicineId));
    }

}
