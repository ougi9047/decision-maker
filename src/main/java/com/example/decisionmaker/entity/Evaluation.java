package com.example.decisionmaker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "evaluations")
@Data
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score; // 1〜10点の点数

    @ManyToOne
    @JoinColumn(name = "alternative_id")
    @JsonBackReference("alternative-evaluations")
    private Alternative alternative;

    @ManyToOne
    @JoinColumn(name = "criteria_id")
    @JsonBackReference("criterion-evaluations")
    private Criterion criterion;
}