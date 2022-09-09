package com.ksy.noticeboard.controller;

import com.ksy.noticeboard.util.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionController {

    private final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    protected String handleSQLException(Exception e, Model model) {
        LOGGER.error("500:sql exception 발생");
        ErrorResponse errorResponse = new ErrorResponse(500, "DB 로직에 오류가 발생했습니다.");
        model.addAttribute("error", errorResponse);
        return "errors/error";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected String handleNotFoundException(Exception e, Model model) {
        LOGGER.error("404:not found error 발생");
        ErrorResponse errorResponse = new ErrorResponse(404, "페이지를 찾을 수 없습니다.");
        model.addAttribute("error", errorResponse);
        return "errors/error";
    }

    @ExceptionHandler(NullPointerException.class)
    protected String handleNullPointerException(Exception e, Model model) {
        LOGGER.error("500:null pointer exception 발생");
        ErrorResponse errorResponse = new ErrorResponse(500, "게시글을 찾을 수 없습니다.");
        model.addAttribute("error", errorResponse);
        return "errors/error";
    }

    @ExceptionHandler(NumberFormatException.class)
    protected String handleNumberFormatException(Exception e, Model model) {
        LOGGER.error("400:number format exception 발생");
        ErrorResponse errorResponse = new ErrorResponse(400, "숫자가 입력되어야 합니다.");
        model.addAttribute("error", errorResponse);
        return "errors/error";
    }
}
