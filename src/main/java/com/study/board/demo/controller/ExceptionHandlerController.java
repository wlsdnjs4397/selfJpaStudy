package com.study.board.demo.controller;

import com.study.board.demo.exception.InvalidAccessException;
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
            NonLoginException.class,
            InvalidAccessException.class
    })
    public ModelAndView businessError(HttpServletRequest request, HttpServletResponse response, Exception e, HandlerMethod handlerMethod){
        String forwardUrl = "/index";
        if(e instanceof NonLoginException){
            forwardUrl = "/login";
        }else if(e instanceof InvalidAccessException){
            forwardUrl = null;
        }

        Utils.sendMessage(response, e.getMessage(), forwardUrl);
        return null;
    }
}
