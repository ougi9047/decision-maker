package com.example.decisionmaker;

import com.example.decisionmaker.entity.*;
import com.example.decisionmaker.repository.DecisionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class DataLoader implements CommandLineRunner {

    private final DecisionRepository repository;

    public DataLoader(DecisionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            Decision decision = new Decision();
            decision.setTitle("就職先の選定");
            decision.setDescription("A社とB社の比較");
            decision.setCriteria(new ArrayList<>());
            decision.setAlternatives(new ArrayList<>());

            // 1. 評価基準の作成 (重みの合計が 1.0 になるように設定)
            Criterion c1 = new Criterion();
            c1.setName("年収");
            c1.setWeight(0.6); // 60%
            c1.setDecision(decision);
            decision.getCriteria().add(c1);

            Criterion c2 = new Criterion();
            c2.setName("ワークライフバランス");
            c2.setWeight(0.4); // 40%
            c2.setDecision(decision);
            decision.getCriteria().add(c2);

            // 2. 選択肢の作成（C社を追加）
            Alternative a1 = new Alternative();
            a1.setName("A社");
            a1.setDecision(decision);

            Alternative a2 = new Alternative();
            a2.setName("B社");
            a2.setDecision(decision);

            Alternative a3 = new Alternative();
            a3.setName("C社(理想)");
            a3.setDecision(decision);

            decision.getAlternatives().add(a1);
            decision.getAlternatives().add(a2);
            decision.getAlternatives().add(a3);

            // 保存（CascadeType.ALL 設定により、子要素も一緒に保存）
            repository.save(decision);

            System.out.println("---- 詳細なテストデータを投入しました ----");
        }
    }
}