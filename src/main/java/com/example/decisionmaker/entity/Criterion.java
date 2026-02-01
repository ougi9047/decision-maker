package com.example.decisionmaker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "criteria")
@Data
public class Criterion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;    // 設定項目
    private Double weight;  // 重み付け

    public Criterion() {}

    @ManyToOne
    @JoinColumn(name = "decision_id")
    @JsonBackReference("decision-criteria")
    private Decision decision;

    @OneToMany(mappedBy = "criterion", cascade = CascadeType.ALL)
    @JsonManagedReference("criterion-evaluations") // Evaluation側の名前とペアにする
    private List<Evaluation> evaluations;
}
