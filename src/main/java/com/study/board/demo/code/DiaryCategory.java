package com.study.board.demo.code;

import com.study.board.demo.code.type.DatabaseCode;

public enum DiaryCategory implements DatabaseCode {

    DAILY("01", "일상"),
    BUSINESS("02", "업무"),
    SPECIAL("03", "특별")
    ;

    private final String code;
    private final String text;

    DiaryCategory(String code, String text) {
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

    public static DiaryCategory from(String s){
        for(DiaryCategory category : DiaryCategory.values()){
            if(category.getCode().equals(s)){
                return category;
            }
        }

        return null;
    }

}
