package com.study.board.demo.controller;

import com.study.board.demo.exception.NonLoginException;
import com.study.board.demo.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {

    @ExceptionHandler({
            NonLoginException.class
    })
    public ModelAndView businessError(HttpServletRequest request, HttpServletResponse response, Exception e, HandlerMethod handlerMethod){
        Utils.sendMessage(response, e.getMessage(), "/member/login");
        return null;
    }
}
