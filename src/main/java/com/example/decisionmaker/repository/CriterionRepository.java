package com.example.decisionmaker.repository;

import com.example.decisionmaker.entity.Criterion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterionRepository extends JpaRepository<Criterion, Long> {
    // 基本的な保存・削除機能は JpaRepository が自動で提供
}