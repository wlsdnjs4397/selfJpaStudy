package com.study.board.demo.entity;

import com.study.board.demo.code.DiaryCategory;
import com.study.board.demo.code.Status;
import com.study.board.demo.code.type.DiaryCategoryConverter;
import com.study.board.demo.code.type.StatusConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name= "T_DIALY")
public class DiaryEntity {
    @Id
    @GeneratedValue
    private Long seq;
    @Convert(converter = DiaryCategoryConverter.class)
    private DiaryCategory category;
    private String title;
    private String contents;
    private String author;
    @Convert(converter = StatusConverter.class)
    private Status status;
    private Date createDate;
    private Date modifyDate;

    private Integer rnum;
}
