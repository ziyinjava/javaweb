package ziyin.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author ziyin
 @create 2019-05-2019/5/22-7:02
 */
public class MyServlet implements Servlet {
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		System.out.println("init");
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public void service(ServletRequest servletRequest, ServletResponse servletResponse)
			throws ServletException, IOException {
		System.out.println("service");
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void destroy() {

	}
}
