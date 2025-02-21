package com.study.board.demo.controller;

import com.study.board.demo.code.EditMode;
import com.study.board.demo.common.annotation.LoginId;
import com.study.board.demo.entity.DiaryEntity;
import com.study.board.demo.domain.DiarySearch;
import com.study.board.demo.exception.InvalidAccessException;
import com.study.board.demo.service.DiaryService;
import com.study.board.demo.utils.Paging;
import com.study.board.demo.utils.ResultMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/list")
    public ModelAndView list(DiarySearch search){
        ModelAndView mav = new ModelAndView();

        int count = diaryService.getCount(search);
        Page<DiaryEntity> list = diaryService.getList(search);
        Paging paging = new Paging("list", count, search.getPage(), search.getPageSize(), search.getBlockSize());

        mav.addObject("list", list);
        mav.addObject("count", count);
        mav.addObject("search", search);
        mav.addObject("paging", paging);
        mav.setViewName("/diary/list");
        return mav;
    }

    @GetMapping("/form")
    public ModelAndView form(@RequestParam(name = "seq", required = false) Integer seq){
        ModelAndView mav = new ModelAndView();
        EditMode mode = EditMode.CREATE;

        DiaryEntity diary = new DiaryEntity();
        if(null != seq && seq > 0){
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
    public ModelAndView view(@PathVariable("seq") Integer seq, @LoginId String loginId){
        ModelAndView mav = new ModelAndView();

        if(null == seq || 1 > seq){
            throw new InvalidAccessException("잘못된 접근입니다.");
        }

        DiaryEntity info = diaryService.getInfo(seq);
        if(null == info){
            throw new InvalidAccessException("잘못된 접근입니다.");
        }

        mav.addObject("info", info);
        mav.addObject("loginId", loginId);
        mav.setViewName("/diary/view");
        return mav;
    }

    @ResponseBody
    @PostMapping("/proc")
    public ResultMessage proc(DiaryEntity diary,
                              @RequestParam(value =  "mode") EditMode mode,
                              @LoginId String loginId){
        String resultMessage = "";

        if(mode == EditMode.CREATE){
            diary.setAuthor(loginId);
            diary.setCreateDate(new Date());
            diaryService.create(diary);
            resultMessage = "등록되었습니다.";
        }else if(mode == EditMode.MODIFY){
            diary.setModifyDate(new Date());
            diaryService.modify(diary);
            resultMessage = "수정되었습니다.";
        }else if(mode == EditMode.DELETE){
            diaryService.remove(diary.getSeq());
            resultMessage = "삭제되었습니다.";
        }

        return new ResultMessage(true, resultMessage);
    }
}
