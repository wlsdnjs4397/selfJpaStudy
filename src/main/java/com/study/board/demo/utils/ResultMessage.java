package com.study.board.demo.utils;

import java.util.HashMap;

public class ResultMessage {

    private boolean isSucceed;
    private String message;
    private HashMap<String, String> param;

    public ResultMessage(boolean isSucceed){
        this.isSucceed = isSucceed;
    }

    public ResultMessage(String message){
        this.message = message;
    }

    public ResultMessage(boolean isSucceed, HashMap param) {
        this.isSucceed = isSucceed;
        this.param = param;
    }

    public ResultMessage(boolean isSucceed, String message) {
        this.isSucceed = isSucceed;
        this.message = message;
    }

    public ResultMessage(boolean isSucceed, String message, HashMap param) {
        this.isSucceed = isSucceed;
        this.message = message;
        this.param = param;
    }

    public boolean isSucceed() {
        return isSucceed;
    }

    public void setSucceed(boolean succeed) {
        isSucceed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, String> getParam() {
        return param;
    }

    public void setParam(HashMap<String, String> param) {
        this.param = param;
    }
}
