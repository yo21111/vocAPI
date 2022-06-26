package com.tf.cs.voc.service;

import com.tf.cs.voc.domain.Compensation;
import com.tf.cs.voc.domain.Objection;
import com.tf.cs.voc.domain.VOC;
import com.tf.cs.voc.dto.CompensationDto;
import com.tf.cs.voc.dto.ObjectionDto;
import com.tf.cs.voc.dto.VocDto;
import org.springframework.transaction.annotation.Transactional;

public interface CommonService {
    @Transactional
    VOC registerVoc(VocDto vocDto);

    //2. 패널티 확인 여부 등록 + 이의 제기 등록
    @Transactional
    Objection registerObjection(Long vocId, ObjectionDto objectionDto);
}
