package com.startup.superbug.data_access.repository;


import com.startup.superbug.entity.Medicine;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    List<Medicine> findByGroupId(int groupId);

}
