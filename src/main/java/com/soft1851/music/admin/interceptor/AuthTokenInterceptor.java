package com.soft1851.music.admin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName AuthTokenInterceptor
 * @Description TODO
 * @Author xiaobinggan
 * @Date 2020/4/22 8:42 上午
 * @Version 1.0
 **/
@Slf4j
@Component
public class AuthTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return false;
    }
}
