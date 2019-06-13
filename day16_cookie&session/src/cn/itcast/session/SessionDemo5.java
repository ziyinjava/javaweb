package cn.itcast.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ziyin
 @create 2019-05-2019/5/28-21:58
 */
@WebServlet("/sessionDemo5")
public class SessionDemo5 extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println(session);
		System.out.println(session.getId());
		response.setContentType("text/html;charset=utf-8");
		String path = response.encodeURL("/day16/sessionDemo6");
		System.out.println(path);
		response.getWriter().print("<a href='" + path + "'>点击</a>");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
}
