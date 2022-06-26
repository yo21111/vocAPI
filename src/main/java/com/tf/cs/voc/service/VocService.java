package com.tf.cs.voc.service;

import com.tf.cs.voc.domain.VOC;

import java.util.List;

public interface VocService {
    //1. voc 목록 조회 - 전체목록
    List<VOC> getVocList();

    //2. "고객사" 또는 "운송사" VOC정보만 확인
    List<VOC> getVocListClassify(String classification);

    //3. voc 목록 조회 - 기사 확인 이전
    List<VOC> getVocListUncheck();

    //4. voc 목록 조회 - 기사 확인 + 이의제기 O
    List<VOC> getVocListCheck();

    //5. voc 목록 조회 - 기사 확인 + 이의제기 X
    List<VOC> getVocListCheck_NoObj();
}
