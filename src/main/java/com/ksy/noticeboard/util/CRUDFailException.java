package com.ksy.noticeboard.util;

public class CRUDFailException extends RuntimeException {
    public CRUDFailException(String message) {
        super(message); // RuntimeException 클래스의 생성자를 호출합니다.
    }
}
