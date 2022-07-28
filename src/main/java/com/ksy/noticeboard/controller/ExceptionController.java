package com.ksy.noticeboard.controller;

import com.ksy.noticeboard.util.ErrorResponse;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    protected String handleSQLException(Exception e, Model model) {
        ErrorResponse errorResponse = new ErrorResponse(500, "DB 접속 오류가 발생했습니다.");
        model.addAttribute("error", errorResponse);
        return "errors/error";
    }

    @ExceptionHandler(NotFoundException.class)
    protected String handleNotFoundException(Exception e, Model model) {
        ErrorResponse errorResponse = new ErrorResponse(404, "게시글을 찾을 수 없습니다.");
        model.addAttribute("error", errorResponse);
        return "errors/error";
    }
}
