package com.ksy.noticeboard.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
    private int id;
    private String title;
    private String content;
    private String write_time;
    private int view;
    private int state; // 0: 비공개, 1: 공개
    private int writer_id;
    private String writer_name;
    private BoardState boardState;
}