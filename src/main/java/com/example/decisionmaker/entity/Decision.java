package com.example.decisionmaker.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "decisions")
@Data // Getter/Setter
public class Decision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    // 1つの意思決定に対し、複数の評価基準がある（1対多）
    @OneToMany(mappedBy = "decision", cascade = CascadeType.ALL)
    @JsonManagedReference("decision-criteria")
    private List<Criterion> criteria;

    // 1つの意思決定に対し、複数の選択肢がある（1対多）
    @OneToMany(mappedBy = "decision", cascade = CascadeType.ALL)
    @JsonManagedReference("decision-alternatives")
    private List<Alternative> alternatives;
}