package com.zbkbl.demo.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author liuyang
 * @description
 * @date 2020/07/20 17:04
 **/
@Component
@Order(1)
@WebFilter(urlPatterns = "/api/v1/test", filterName = "secondFilter")
public class SecondFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("do SecondFilter ()开始执行：发往 " + ((HttpServletRequest) request).getRequestURL().toString() + " 的请求已被拦截");
        System.out.println("检验接口是否被调用，尝试获取contentType如下： " + response.getContentType());

        // filter的链式调用；将请求转给下一条过滤链
        chain.doFilter(request, response);

        System.out.println("检验接口是否被调用，尝试获取contentType如下： " + response.getContentType());

        System.out.println("do SecondFilter()执行结束。");
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("SecondFilter init .....");
    }

    @Override
    public void destroy() {
        System.out.println("SecondFilter destroy ....");
    }
}
