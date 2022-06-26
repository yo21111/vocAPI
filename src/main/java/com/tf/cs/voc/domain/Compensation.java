package com.tf.cs.voc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Compensation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String company_id;
    private String classification;
    private String unique_id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "penalty_id")
    private Penalty penalty;
    private LocalDateTime register_date;

    @Builder
    public Compensation(Company company, Penalty penalty) {
        this.company_id = company.getCompany_id();
        this.classification = company.getClassification();
        this.unique_id = company.getUnique_id();
        this.penalty = penalty;
        this.register_date = LocalDateTime.now();
    }
}
