package com.tf.cs.voc.service;

import com.tf.cs.voc.domain.VOC;
import com.tf.cs.voc.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VocServiceImpl implements VocService {
    private final VocRepository vocRepo;
    public final VocRepositorySupport support;

    //1. voc 목록 조회 - 전체목록
    @Override
    public List<VOC> getVocList() {
        return vocRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    //2. "고객사" 또는 "운송사" VOC정보만 확인
    @Override
    public List<VOC> getVocListClassify(String classification) {
        if (!(classification.equals("운송사") || classification.equals("고객사"))) {
            throw new IllegalArgumentException();
        }
        return vocRepo.findAll(equalClassification(classification), Sort.by(Sort.Direction.DESC, "id"));
    }

    private Specification<VOC> equalClassification(String classification) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("classification"), classification);
    }


    //3. voc 목록 조회 - 기사 확인 이전
    @Override
    public List<VOC> getVocListUncheck() {
        return support.findVocList_uncheck();
    }

    //4. voc 목록 조회 - 기사 확인 + 이의제기 O
    @Override
    public List<VOC> getVocListCheck() {
        return support.findVocList_check_objection();
    }

    //5. voc 목록 조회 - 기사 확인 + 이의제기 X
    @Override
    public List<VOC> getVocListCheck_NoObj() {
        return support.findVocList_check_noObjection();
    }
}
