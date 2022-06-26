package com.tf.cs.voc.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDto {
    private String msg;
    private LocalDateTime time;

    public ErrorDto(String msg) {
        this.msg = msg;
        this.time = LocalDateTime.now();
    }
}
