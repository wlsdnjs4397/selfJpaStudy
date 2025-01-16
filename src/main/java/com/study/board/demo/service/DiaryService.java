package com.study.board.demo.service;

import com.study.board.demo.code.Status;
import com.study.board.demo.entity.DiaryEntity;
import com.study.board.demo.repository.DiaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    public DiaryEntity diaryBySeq(Integer seq) {
        return diaryRepository.findBySeq(seq);
    }

    public void create(DiaryEntity diary) {
        diaryRepository.save(diary);
    }

    public void modify(DiaryEntity diary) {
    }

    public void remove(DiaryEntity diary) {
    }

    public List<DiaryEntity> getList(Status status) {
        return diaryRepository.findByStatus(status);
    }

    public int getCount(Status status) {
        return diaryRepository.countByStatus(status);
    }

    public DiaryEntity getInfo(Integer seq) {
        return diaryRepository.findBySeq(seq);
    }
}
