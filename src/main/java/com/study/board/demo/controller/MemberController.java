package com.study.board.demo.controller;

import com.study.board.demo.common.annotation.PublicAccess;
import com.study.board.demo.entity.MemberEntity;
import com.study.board.demo.service.MemberService;
import com.study.board.demo.utils.ResultMessage;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private MemberService memberService;

    @PublicAccess
    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/login");
        return mav;
    }

    @PublicAccess
    @PostMapping("/login-proc")
    @ResponseBody
    public ResultMessage loginProc(HttpSession session,
                                   @RequestParam(value = "loginId") String loginId,
                                   @RequestParam(value = "password") String password){
        MemberEntity loginMember = new MemberEntity();
        loginMember.setLoginId(loginId);
        loginMember.setPassword(password);
        String code = memberService.login(loginMember);
        String message = code.equals("01") ? "잘못된 아이디 또는 비밀번호 입니다." : "";

        session.setAttribute("loginId", loginMember.getLoginId());
        return new ResultMessage(code.equals("00"), message);
    }

    @PublicAccess
    @GetMapping("/join")
    public ModelAndView join(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/member/join");
        return mav;
    }

    @PublicAccess
    @PostMapping("/id-check")
    @ResponseBody
    public ResultMessage idCheck(@RequestParam(value = "loginId") String loginId){
        MemberEntity member = memberService.memberByLoginId(loginId);
        return new ResultMessage(null == member);
    }

    @PublicAccess
    @PostMapping("/join-proc")
    @ResponseBody
    public ResultMessage joinProc(MemberEntity member){
        member.setSignUpDate(new Date());
        String validCheck = member.validCheckMember();
        if(!validCheck.equals("")){
            return new ResultMessage(false, validCheck);
        }

        memberService.saveMember(member);
        return new ResultMessage(true, "회원가입이 완료되었습니다.");
    }
}
