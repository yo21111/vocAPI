package com.tf.cs.voc.domain;

import com.tf.cs.voc.dto.PenaltyDto;
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
public class Penalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shipping_no;
    private String penalty_Content;
    private Integer penalty_price;
    private LocalDateTime register_date;

    @Builder
    public Penalty(PenaltyDto penaltyDto) {
        this.shipping_no = penaltyDto.getShipping_no();
        this.penalty_Content = penaltyDto.getPenalty_Content();
        this.penalty_price = penaltyDto.getPenalty_price();
        this.register_date = LocalDateTime.now();
    }
}
