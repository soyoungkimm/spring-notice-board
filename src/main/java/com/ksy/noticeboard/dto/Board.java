package com.ksy.noticeboard.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class Board {
    private int id;

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 값입니다.")
    private String content;

    private String write_time;
    private int view;
    private int state; // 0: 비공개, 1: 공개
    private int writer_id;
}
