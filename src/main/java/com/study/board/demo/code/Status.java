package com.study.board.demo.code;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.study.board.demo.code.type.DatabaseCode;

public enum Status implements DatabaseCode {
    SHOW("S", "공개"),
    HIDDEN("H", "비공개"),
    ;

    private final String code;
    private final String text;

    Status(String code, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static Status from(String s){
        for(Status status : Status.values()){
            if(status.getCode().equals(s)){
                return status;
            }
        }

        return null;
    }

}
