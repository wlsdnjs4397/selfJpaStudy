package com.study.board.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class DiaryEntity {
    @Id
    @GeneratedValue
    private String seq;
    private String title;
    private String contents;
    private String writer;
    private String publicYn;
    private Date createDate;
    private Date modifyDate;
}
