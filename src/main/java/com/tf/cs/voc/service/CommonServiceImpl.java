package com.tf.cs.voc.service;

import com.tf.cs.voc.domain.*;
import com.tf.cs.voc.dto.*;
import com.tf.cs.voc.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {
    private final VocRepository vocRepo;
    private final CompensationRepository compRepo;
    private final PenaltyRepository penaltyRepo;
    private final ObjectionRepository objRepo;

    //1. VOC 등록
    @Override
    @Transactional
    public VOC registerVoc(VocDto vocDto) {
        Penalty penalty = penaltyRepo.save(vocDto.getCompensation().getPenalty());
        Company company = new Company(vocDto.getCompensation().getCompany_id(), vocDto.getCompensation().getClassification(), vocDto.getCompensation().getUnique_id());
        Compensation comp = compRepo.save(Compensation.builder().company(company).penalty(penalty).build());
        Objection objection = objRepo.save(vocDto.getObjection());
        return vocRepo.save(VOC.builder().claiming(vocDto.getClaiming()).compensation(comp).objection(objection).build());
    }


    //4. 패널티 확인 여부 등록 + 이의 제기 등록
    @Override
    @Transactional
    public Objection registerObjection(Long vocId, ObjectionDto objectionDto) {
        Optional<VOC> voc = vocRepo.findById(vocId);
        Objection objection = voc.orElseThrow(IllegalArgumentException::new).getObjection();
        objection.object_content(objectionDto.getObjection_content());
        return objRepo.save(objection);
    }
}
