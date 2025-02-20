package com.study.board.demo.domain;

import com.study.board.demo.code.DiaryCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
public class DiarySearch {

    private int page = 1;
    private String title;
    private String author;
    private DiaryCategory category;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

}
