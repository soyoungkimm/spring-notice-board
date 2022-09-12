package com.ksy.noticeboard.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    @ApiParam(value="id", example = "0")
    private int id;

    @ApiParam(value="이름")
    private String name;

    @ApiParam(value="아이디")
    private String uid;

    @ApiParam(value="패스워드")
    private String pwd;
}
