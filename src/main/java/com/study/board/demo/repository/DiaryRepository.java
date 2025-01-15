package com.study.board.demo.repository;

import com.study.board.demo.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    DiaryEntity findBySeq(Integer seq);
}
