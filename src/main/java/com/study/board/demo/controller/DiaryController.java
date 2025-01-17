package com.study.board.demo.controller;

import com.study.board.demo.code.DiaryCategory;
import com.study.board.demo.code.EditMode;
import com.study.board.demo.code.Status;
import com.study.board.demo.common.annotation.LoginId;
import com.study.board.demo.common.annotation.PublicAccess;
import com.study.board.demo.entity.DiaryEntity;
import com.study.board.demo.exception.InvalidAccessException;
import com.study.board.demo.service.DiaryService;
import com.study.board.demo.utils.ResultMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView();

        int count = diaryService.getCount(Status.SHOW);
        List<DiaryEntity> list = diaryService.getList(Status.SHOW);

        mav.addObject("list", list);
        mav.addObject("count", count);
        mav.setViewName("/diary/list");
        return mav;
    }

    @GetMapping("/form")
    public ModelAndView form(@RequestParam(required = false) Integer seq){
        ModelAndView mav = new ModelAndView();
        EditMode mode = EditMode.CREATE;

        DiaryEntity diary = new DiaryEntity();
        if(null != seq && 0 > seq){
            diary = diaryService.diaryBySeq(seq);
            if(null != diary){
                mode = EditMode.MODIFY;
            }
        }

        mav.addObject("info", diary);
        mav.addObject("mode", mode);
        mav.setViewName("/diary/form");
        return mav;
    }

    @GetMapping("/view/{seq}")
    public ModelAndView view(@PathVariable("seq") Integer seq){
        ModelAndView mav = new ModelAndView();

        if(null == seq || 1 < seq){
            throw new InvalidAccessException("잘못된 접근입니다.");
        }

        DiaryEntity info = diaryService.getInfo(seq);
        if(null == info){
            throw new InvalidAccessException("잘못된 접근입니다.");
        }



        return mav;
    }

    @ResponseBody
    @PostMapping("/proc")
    public ResultMessage proc(DiaryEntity diary,
                              @RequestParam(value =  "mode") EditMode mode,
                              @LoginId String loginId){
        String resultMessage = "";

        if(mode == EditMode.CREATE){
            diary.setWriter(loginId);
            diary.setCreateDate(new Date());
            diaryService.create(diary);
            resultMessage = "등록되었습니다.";
        }else if(mode == EditMode.MODIFY){
            diary.setModifyDate(new Date());
            diaryService.modify(diary);
            resultMessage = "수정되었습니다.";
        }else if(mode == EditMode.DELETE){
            diaryService.remove(diary);
            resultMessage = "삭제되었습니다.";
        }

        return new ResultMessage(true, resultMessage);
    }
}
