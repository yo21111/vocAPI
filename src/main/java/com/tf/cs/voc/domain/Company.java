package com.tf.cs.voc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private String company_id;
    private String classification;
    private String unique_id;

    public Company(String classification) {
        this.company_id = UUID.randomUUID().toString();
        this.classification = classification;
        this.unique_id = UUID.randomUUID().toString();
    }

}
