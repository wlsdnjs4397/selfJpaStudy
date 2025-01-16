package com.study.board.demo.config;

import com.study.board.demo.code.type.DatabaseCodeConverterFactory;
import com.study.board.demo.common.resolver.LoginIdArgumentResolver;
import com.study.board.demo.interceptor.LayoutInterceptor;
import com.study.board.demo.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final MemberService memberService;

    private final LoginIdArgumentResolver loginIdArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LayoutInterceptor(memberService))
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/image/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/js/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new DatabaseCodeConverterFactory());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){
        resolvers.add(loginIdArgumentResolver);
    }
}
