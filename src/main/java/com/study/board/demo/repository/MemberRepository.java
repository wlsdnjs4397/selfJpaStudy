package com.study.board.demo.repository;

import com.study.board.demo.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByLoginId(String loginId);
}
