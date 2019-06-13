package cn.itcast.web.filter;

import javax.servlet.*;
import java.io.IOException;

//@WebFilter("/*")
public class FilterDemo6 implements Filter {


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("filterDemo6执行了...");

        chain.doFilter(req, resp);

        System.out.println("filterDemo6回来了...");
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

}
