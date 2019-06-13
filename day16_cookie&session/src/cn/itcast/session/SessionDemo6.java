package cn.itcast.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ziyin
 @create 2019-05-2019/5/28-21:58
 */
@WebServlet("/sessionDemo6")
public class SessionDemo6 extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		System.out.println(request.getSession().getId());
		System.out.println(request.getSession());
		response.getWriter().print("你的ip是 " + request.getRemoteAddr());

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
}
