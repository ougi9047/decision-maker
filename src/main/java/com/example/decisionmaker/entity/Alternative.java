package com.example.decisionmaker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "alternatives")
@Data
public class Alternative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 選択肢の名前

    @ManyToOne
    @JoinColumn(name = "decision_id")
    @JsonBackReference("decision-alternatives") // 親（Decision）へ戻るループをカット
    private Decision decision;

    // 今後のスコア計算のために、自分に紐づく評価点数のリストも保持
    @OneToMany(mappedBy = "alternative", cascade = CascadeType.ALL)
    @JsonManagedReference("alternative-evaluations") // 自分から子（Evaluation）への書き出しは許可
    private List<Evaluation> evaluations;
}