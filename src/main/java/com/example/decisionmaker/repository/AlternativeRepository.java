package com.example.decisionmaker.repository;

import com.example.decisionmaker.entity.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlternativeRepository extends JpaRepository<Alternative, Long> {
    // 基本的な保存、削除、ID検索機能
}