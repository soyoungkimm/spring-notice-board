package com.ksy.noticeboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private int id;
    private String title;
    private String content;
    private String write_time;
    private int view;
    private int state; // 0: 공개, 1: 비공개
    private int writer_id;
}
