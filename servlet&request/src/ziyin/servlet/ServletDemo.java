package ziyin.servlet;

/**
 * @author ziyin
 @create 2019-05-2019/5/22-7:09
 */

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/servletdemo")
//@WebServlet("*.do")
//@WebServlet("/*")  //如果在servlet的匹配路径下优先级最低,但是会拦截所有静态资源的访问, 比如http://localhost/day14/requestDemo3,也会进入这个servlet中
public class ServletDemo implements Servlet {
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		System.out.println("servletdemo....init");
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public void service(ServletRequest servletRequest, ServletResponse servletResponse)
			throws ServletException, IOException {
		System.out.println("servletdemo....service");


	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void destroy() {

	}
}
