package com.study.board.demo.repository;

import com.study.board.demo.code.Status;
import com.study.board.demo.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> , JpaSpecificationExecutor<DiaryEntity> {
    DiaryEntity findBySeq(Integer seq);
    List<DiaryEntity> findByStatus(Status status);
    void deleteBySeq(Long seq);
}
