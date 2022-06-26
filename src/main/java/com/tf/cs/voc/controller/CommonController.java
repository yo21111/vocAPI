package com.tf.cs.voc.controller;

import com.tf.cs.voc.dto.*;
import com.tf.cs.voc.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommonController {
    private final CommonService commonService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorDto exceptionHandle(IllegalArgumentException e) {
        e.printStackTrace();
        return new ErrorDto("잘못된 요청 정보입니다.");
    }

    //1. voc 등록
    @PostMapping("/voc")
    public void  registerVoc(@RequestBody VocDto vocDto) {
        commonService.registerVoc(vocDto);
    }


    //2. 패널티 확인 여부 등록 + 이의 제기 등록
    @PostMapping("/objection")
    public void registerObjection(@RequestBody VocDto vocDto, @RequestBody ObjectionDto objectionDto) {
        commonService.registerObjection(vocDto.getId(), objectionDto);
    }
}
