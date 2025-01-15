package com.study.board.demo.service;

import com.study.board.demo.entity.MemberEntity;
import com.study.board.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository repository) {
        this.memberRepository = repository;
    }

    public void saveMember(MemberEntity member){
        memberRepository.save(member);
    }

    public MemberEntity memberByLoginId(String loginId){
        return memberRepository.findByLoginId(loginId);
    }

    public String login(MemberEntity loginMember) {
        MemberEntity member = memberByLoginId(loginMember.getLoginId());
        return null == member || !loginMember.getPassword().equals(member.getPassword()) ? "01" : "00";
    }

}
