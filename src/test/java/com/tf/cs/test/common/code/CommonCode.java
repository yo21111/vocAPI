package com.tf.cs.test.common.code;

import com.tf.cs.voc.domain.Company;
import com.tf.cs.voc.domain.Compensation;
import com.tf.cs.voc.domain.Objection;
import com.tf.cs.voc.domain.Penalty;
import com.tf.cs.voc.dto.CompensationDto;
import com.tf.cs.voc.dto.ObjectionDto;
import com.tf.cs.voc.dto.PenaltyDto;
import com.tf.cs.voc.dto.VocDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommonCode {
    public VocDto createVocDto(String classification, int penalty_price) {
        VocDto vocDto = new VocDto();
        Company companyA = new Company(classification);
        Company companyB = new Company(classification.equals("운송사") ? "운송사" : "고객사");

        vocDto.setClaiming(companyA);

        String shipping_no = UUID.randomUUID().toString();
        PenaltyDto penaltyDto = createPenaltyDto(shipping_no, "배송 중 물건 누락", penalty_price);
        CompensationDto compDto = createCompDto(companyB, penaltyDto);
        vocDto.setCompensation(Compensation.builder().company(companyB).penalty(compDto.getPenalty()).build());

        String unique_id = UUID.randomUUID().toString();
        ObjectionDto objDto = createObjDto(shipping_no, unique_id);
        vocDto.setObjection(Objection.builder().objectionDto(objDto).build());
        return vocDto;
    }

    public CompensationDto createCompDto(Company company, PenaltyDto penaltyDto) {
        CompensationDto compDto = new CompensationDto();
        compDto.setCompany(company);
        compDto.setPenalty(Penalty.builder().penaltyDto(penaltyDto).build());
        return compDto;
    }

    public PenaltyDto createPenaltyDto(String shipping_no, String content, int price) {
        PenaltyDto penaltyDto = new PenaltyDto();
        penaltyDto.setShipping_no(shipping_no);
        penaltyDto.setPenalty_price(price);
        penaltyDto.setPenalty_Content(content);
        return penaltyDto;
    }

    public ObjectionDto createObjDto(String shipping_no, String unique_id) {
        ObjectionDto objectionDto = new ObjectionDto();
        objectionDto.setShipping_no(shipping_no);
        objectionDto.setUnique_id(unique_id);
        return objectionDto;
    }

}
