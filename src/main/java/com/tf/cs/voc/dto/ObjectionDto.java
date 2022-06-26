package com.tf.cs.voc.dto;

import com.tf.cs.voc.domain.Objection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ObjectionDto {
    private Long id;
    private String unique_id;
    private String shipping_no;
    private LocalDateTime confirm_date;
    private String objection_content;

    public ObjectionDto(Objection objection) {
        this.id = objection.getId();
        this.unique_id = objection.getUnique_id();
        this.shipping_no = objection.getShipping_no();
        this.confirm_date = objection.getConfirm_date();
        this.objection_content = objection.getObjection_content();
    }
}
