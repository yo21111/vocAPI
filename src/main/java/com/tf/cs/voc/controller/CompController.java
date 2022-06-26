package com.tf.cs.voc.controller;

import com.tf.cs.voc.domain.Compensation;
import com.tf.cs.voc.dto.ErrorDto;
import com.tf.cs.voc.service.CompService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompController {
    private final CompService compService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorDto exceptionHandle(IllegalArgumentException e) {
        e.printStackTrace();
        return new ErrorDto("잘못된 요청 정보입니다.");
    }

    //1. 배상정보 리스트 조회
    @GetMapping("/comp")
    public List<Compensation> getCompList() {
        return compService.findCompList();
    }

    //2.배상 청구가 없는 배상정보 리스트 조회
    @GetMapping("/comp/noPenalty")
    public List<Compensation> getCompList_NoPenalty() {
        return compService.findCompList_NoPenalty();
    }

    //3.배상 청구가 없는 배상정보 리스트 조회
    @GetMapping("/comp/penalty")
    public List<Compensation> getCompList_Penalty() {
        return compService.findCompList_Penalty();
    }
}
