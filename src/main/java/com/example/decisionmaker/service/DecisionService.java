package com.example.decisionmaker.service;

import com.example.decisionmaker.entity.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DecisionService {

    public Map<String, Object> calculateDetailedResult(Decision decision) {
        Map<String, Object> result = new HashMap<>();

        // グラフのラベル（項目名）
        List<String> labels = decision.getCriteria().stream().map(Criterion::getName).toList();
        result.put("labels", labels);

        Map<String, List<Integer>> companies = new HashMap<>();
        Map<String, Double> rankings = new HashMap<>(); // ランキング用

        for (Alternative alt : decision.getAlternatives()) {
            List<Integer> scores = new ArrayList<>();
            double totalWeightedScore = 0.0;

            for (Criterion crit : decision.getCriteria()) {
                Integer score = alt.getEvaluations().stream()
                        .filter(e -> e.getCriterion().getId().equals(crit.getId()))
                        .map(Evaluation::getScore)
                        .findFirst().orElse(0);
                scores.add(score);

                // 重みをかけた合計を計算
                totalWeightedScore += score * crit.getWeight();
            }
            companies.put(alt.getName(), scores);
            rankings.put(alt.getName(), Math.round(totalWeightedScore * 10.0) / 10.0); // 小数第1位まで
        }
        result.put("companies", companies);
        result.put("rankings", rankings); // ランキングデータを追加

        return result;
    }
}