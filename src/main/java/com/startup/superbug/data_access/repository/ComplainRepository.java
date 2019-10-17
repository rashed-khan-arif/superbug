package com.startup.superbug.data_access.repository;

import com.startup.superbug.entity.Complain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplainRepository extends JpaRepository<Complain,Integer> {
}
