package com.study.board.demo.repository;

import com.study.board.demo.code.DiaryCategory;
import com.study.board.demo.code.Status;
import com.study.board.demo.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> , JpaSpecificationExecutor<DiaryEntity> {
    DiaryEntity findBySeq(Integer seq);
    void deleteBySeq(Long seq);

    @Modifying
    @Transactional
    @Query("UPDATE DiaryEntity d " +
            "SET d.title = :title, d.contents = :contents, d.category = :category, d.modifyDate = CURRENT_TIMESTAMP " +
            "WHERE d.seq=:seq")
    int updateBySeq(@Param("seq") long seq,
                    @Param("category") DiaryCategory category,
                    @Param("title") String title,
                    @Param("contents") String contents);
}
