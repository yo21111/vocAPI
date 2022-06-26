package com.tf.cs.voc.dto;

import com.tf.cs.voc.domain.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VocDto {
    private Long id;
    private Company claiming;
    private Compensation compensation;
    private Objection objection;

    public VocDto(VOC voc) {
        this.claiming.setUnique_id(voc.getUnique_id());
        this.claiming.setClassification(voc.getClassification());
        this.claiming.setCompany_id(voc.getCompany_id());
        this.compensation = voc.getCompensation();
        this.objection = voc.getObjection();
    }
}
