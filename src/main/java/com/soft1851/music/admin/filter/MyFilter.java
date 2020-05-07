package com.soft1851.music.admin.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * @author mq_xu
 * @ClassName MyFilter
 * @description TODO
 * @date 2020/5/2
 */
@Order(1)
@WebFilter(filterName = "myFilter", urlPatterns = "/*")
@Slf4j
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("执行过滤器");

        if (servletRequest instanceof HttpServletRequest) {
            log.info("有http请求进入过滤器");
            // 防止流读取一次后就没有了, 所以需要将流继续写出去
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String header = httpServletRequest.getHeader("Content-Type");
            if (header != null) {
                log.info(header);
                if (header.startsWith("multipart/form-data")) {
                    log.info("有文件上传请求");
                    Part file = httpServletRequest.getPart("file");
                    log.info("文件：" + file);
                }
            }
            // 这里将原始request传入，读出流并存储
            ServletRequest requestWrapper = new BodyReaderRequestWrapper(httpServletRequest);
            // 这里将原始request替换为包装后的request，此后所有进入controller的request均为包装后的
            filterChain.doFilter(requestWrapper, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}