package com.ksy.noticeboard.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import io.swagger.annotations.ApiParam;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @ApiParam(value="글 id", example = "1")
    @Min(value=1)
    private int id;

    @ApiParam(value="제목", required = true)
    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @ApiParam(value="내용", required = true)
    @NotBlank(message = "내용은 필수 값입니다.")
    private String content;

    @ApiParam(value="작성 시간")
    @NotBlank
    private String writeTime;

    @ApiParam(value="조회수", example = "0")
    @Min(value=0)
    private int viewCount;

    @ApiParam(value="상태 : 0-비공개, 1-공개", example = "1")
    @Min(value=0) @Max(value=1)
    private int state; // 0: 비공개, 1: 공개

    @ApiParam(value="작성자 id", example = "1")
    @Min(value=1)
    private int writerId;
}
