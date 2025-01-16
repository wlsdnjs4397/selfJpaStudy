package com.study.board.demo.service;

import com.study.board.demo.entity.DiaryEntity;
import com.study.board.demo.repository.DiaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
