package com.example.decisionmaker.repository;

import com.example.decisionmaker.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    // 特定の「選択肢」と「評価基準」の組み合わせに一致する評価データを1件探す
    Optional<Evaluation> findByAlternativeIdAndCriterionId(Long alternativeId, Long criterionId);

    @Transactional
    void deleteByCriterionDecisionId(Long decisionId);
}