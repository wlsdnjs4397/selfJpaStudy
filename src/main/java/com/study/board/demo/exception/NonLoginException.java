package com.study.board.demo.exception;

public class NonLoginException extends RuntimeException {

    public NonLoginException(String message){
        super(message);
    }
}
