package com.tf.cs.voc.dto;

import com.tf.cs.voc.domain.Company;
import com.tf.cs.voc.domain.Compensation;
import com.tf.cs.voc.domain.Penalty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CompensationDto {
    private Long id;
    private Company company;
    private Penalty penalty;
    private LocalDateTime register_date;

    public CompensationDto(Compensation compensation) {
        this.company.setCompany_id(compensation.getCompany_id());
        this.company.setClassification(compensation.getClassification());
        this.company.setUnique_id(compensation.getUnique_id());
        this.penalty = compensation.getPenalty();
        this.register_date = compensation.getRegister_date();
    }
}
