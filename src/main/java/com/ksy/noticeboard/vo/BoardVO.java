package com.ksy.noticeboard.vo;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
    private int id;
    private String title;
    private String content;
    private String writeTime;
    private int viewCount;
    private int state; // 0: 비공개, 1: 공개
    private int writerId;
    private String writerName;
    private BoardState boardState;
}