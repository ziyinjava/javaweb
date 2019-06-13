package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName="AsynFilter",asyncSupported=true,value={"/index.jsp"},dispatcherTypes={/*DispatcherType.REQUEST,*/DispatcherType.ASYNC})
public class AsynFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        System.out.println("Start.....AsynFilter");
        arg2.doFilter(arg0, arg1);
        System.out.println("End....AsynFilter");
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}