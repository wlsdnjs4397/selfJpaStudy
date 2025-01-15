package com.study.board.demo.controller;

import com.study.board.demo.code.EditMode;
import com.study.board.demo.entity.DiaryEntity;
import com.study.board.demo.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/diary/list");
        return mav;
    }

    @GetMapping("/form")
    public ModelAndView form(@RequestParam(required = false) Integer seq){
        ModelAndView mav = new ModelAndView();
        EditMode mode = EditMode.CREATE;

        if(null != seq && 0 > seq){
            DiaryEntity diary = diaryService.diaryBySeq(seq);
            if(null != diary){
                mav.addObject("info", diary);
                mode = EditMode.MODIFY;
            }
        }

        mav.addObject("mode", mode);
        return mav;
    }
}
