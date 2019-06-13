package cn.itcast.web.filter;

import javax.servlet.*;
import java.io.IOException;

//@WebFilter("/*")
public class FilterDemo17 implements Filter {


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("filterDemo7执行了...");

        chain.doFilter(req, resp);

        System.out.println("filterDemo7回来了...");
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

}
