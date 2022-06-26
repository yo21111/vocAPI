package com.tf.cs.voc.domain;

import com.tf.cs.voc.dto.ObjectionDto;
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
public class Objection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String unique_id;
    private String shipping_no;
    private LocalDateTime confirm_date;
    private String objection_content;

    @Builder
    public Objection(ObjectionDto objectionDto) {
        this.id = objectionDto.getId();
        this.unique_id = objectionDto.getUnique_id();
        this.shipping_no = objectionDto.getShipping_no();
        if(objectionDto.getObjection_content() != null) {
            this.confirm_date = LocalDateTime.now();
        }
        this.objection_content = objectionDto.getObjection_content();
    }

    public void object_content(String objection_content) {
        this.objection_content = objection_content;
        this.confirm_date = LocalDateTime.now();
    }
}
