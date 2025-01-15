package com.study.board.demo.config;

import com.study.board.demo.interceptor.LayoutInterceptor;
import com.study.board.demo.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final MemberService memberService;
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LayoutInterceptor(memberService))
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/image/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/js/**");
    }
}
