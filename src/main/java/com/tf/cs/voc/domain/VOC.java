package com.tf.cs.voc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VOC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String company_id;
    private String classification;
    private String unique_id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "compensation_id")
    private Compensation compensation;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "objection_id")
    private Objection objection;

    @Builder
    public VOC(Company claiming, Compensation compensation, Objection objection) {
        this.company_id = claiming.getCompany_id();
        this.classification = claiming.getClassification();
        this.unique_id = claiming.getUnique_id();
        this.compensation = compensation;
        this.objection = objection;
    }
}
