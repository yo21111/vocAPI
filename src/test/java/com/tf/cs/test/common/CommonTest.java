package com.tf.cs.test.common;

import com.tf.cs.test.common.code.CommonCode;
import com.tf.cs.voc.domain.Compensation;
import com.tf.cs.voc.domain.Objection;
import com.tf.cs.voc.domain.Penalty;
import com.tf.cs.voc.domain.VOC;
import com.tf.cs.voc.dto.ObjectionDto;
import com.tf.cs.voc.dto.VocDto;
import com.tf.cs.voc.repository.CompensationRepository;
import com.tf.cs.voc.repository.ObjectionRepository;
import com.tf.cs.voc.repository.PenaltyRepository;
import com.tf.cs.voc.repository.VocRepository;
import com.tf.cs.voc.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Common 테스트")
public class CommonTest {
    @Autowired
    CommonService commonService;
    @Autowired
    CommonCode commonCode;
    @Autowired
    VocRepository vocRepository;
    @Autowired
    ObjectionRepository objectionRepository;
    @Autowired
    CompensationRepository compensationRepository;
    @Autowired
    PenaltyRepository penaltyRepository;

    @Test
    @DisplayName("VOC 등록")
    void registerVoc() {
        VocDto vocDto = commonCode.createVocDto("고객사", 10000);
        VOC voc = commonService.registerVoc(vocDto);
        log.info("voc={}",voc.toString());
    }


    @Test
    @DisplayName("이의제기 등록")
    void registerObjection() {
        VocDto vocDto = commonCode.createVocDto("고객사", 10000);
        VOC voc = commonService.registerVoc(vocDto);

        Long id = voc.getId();
        Objection objBefore = voc.getObjection();

        ObjectionDto objDto = new ObjectionDto();
        objDto.setObjection_content("이의제기 합니다.");
        Objection objectionAfter = commonService.registerObjection(id, objDto);

        log.info("beforeObj: id={}, content={}, register_date={}", objBefore.getId(), objBefore.getObjection_content(), objBefore.getConfirm_date());
        log.info("afterObj: id={}, content={}, register_date={}", objectionAfter.getId(), objectionAfter.getObjection_content(), objectionAfter.getConfirm_date());
        assertThat(objBefore.getId()).isEqualTo(objectionAfter.getId());
    }

}
