package com.startup.superbug.data_access.repository;

import com.startup.superbug.entity.Medicine;
import com.startup.superbug.entity.MedicineGroup;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineGroupRepository extends JpaRepository<MedicineGroup, Integer> {

}
