package com.study.board.demo.interceptor;

import com.study.board.demo.common.annotation.PublicAccess;
import com.study.board.demo.entity.MemberEntity;
import com.study.board.demo.exception.NonLoginException;
import com.study.board.demo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@RequiredArgsConstructor
public class LayoutInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        PublicAccess publicAccess = ((HandlerMethod) handler).getMethodAnnotation(PublicAccess.class);
        boolean publicPage = publicAccess != null;
        if (!publicPage) {
            String loginId = getLoginId(request);
            if (null == loginId) {
                throw new NonLoginException("로그인 후 이용해주세요.");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
        PublicAccess publicAccess = ((HandlerMethod) handler).getMethodAnnotation(PublicAccess.class);
        boolean publicPage = publicAccess != null;
        if (mav != null && !publicPage) {
            String loginId = getLoginId(request);
            if (null != loginId) {
               header(loginId, mav);
            }
        }
    }

    public void header(String loginId, ModelAndView mav){
        MemberEntity member = memberService.memberByLoginId(loginId);
        mav.addObject("nickName", member.getNickName());
    }

    private String getLoginId(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("loginId");
    }
}
