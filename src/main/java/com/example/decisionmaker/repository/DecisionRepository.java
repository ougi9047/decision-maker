package com.example.decisionmaker.repository;

import com.example.decisionmaker.entity.Decision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecisionRepository extends JpaRepository<Decision, Long> {
    // save(), findById(), findAll()
}