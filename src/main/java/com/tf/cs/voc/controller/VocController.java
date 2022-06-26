package com.tf.cs.voc.controller;

import com.tf.cs.voc.domain.VOC;
import com.tf.cs.voc.dto.ErrorDto;
import com.tf.cs.voc.service.VocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VocController {
    public final VocService vocService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorDto exceptionHandle(IllegalArgumentException e) {
        e.printStackTrace();
        return new ErrorDto("잘못된 요청 정보입니다.");
    }

    //1. voc 목록 조회 - 전체목록
    @GetMapping("/voc")
    public List<VOC> getVocList() {
        return vocService.getVocList();
    }

    //2. "고객사" 또는 "운송사" VOC정보만 확인
    @GetMapping("/voc/{classification}")
    public List<VOC> getVocListClassify(@PathVariable String classification) {
        return vocService.getVocListClassify(classification);
    }

    //3. voc 목록 조회 - 기사 확인 이전
    @GetMapping("/voc/unchecked")
    public List<VOC> getVocListUncheck() {
        return vocService.getVocListUncheck();
    }

    //4. voc 목록 조회 - 기사 확인 + 이의제기 O
    @GetMapping("/voc/checked")
    public List<VOC> getVocListCheck() {
        return vocService.getVocListCheck();
    }

    //5. voc 목록 조회 - 기사 확인 + 이의제기 X
    @GetMapping("/voc/checked_noObj")
    public List<VOC> getVocListCheck_NoObj() {
        return vocService.getVocListCheck_NoObj();
    }
}
