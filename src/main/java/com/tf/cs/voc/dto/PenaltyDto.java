package com.tf.cs.voc.dto;

import com.tf.cs.voc.domain.Penalty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PenaltyDto {
    private Long id;
    private String shipping_no;
    private String penalty_Content;
    private Integer penalty_price;
    private LocalDateTime register_date;

    public PenaltyDto(Penalty penalty) {
        this.id = penalty.getId();
        this.shipping_no = penalty.getShipping_no();
        this.penalty_Content = penalty.getPenalty_Content();
        this.penalty_price = penalty.getPenalty_price();
        this.register_date = LocalDateTime.now();
    }
}
