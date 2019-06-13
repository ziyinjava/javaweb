package cn.itcast.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ziyin
 @create 2019-05-2019/5/27-15:19
 */
@WebServlet("/CD1")
public class CD1 extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		Cookie cookie = new Cookie("msg","hel");
		Cookie c2 = new Cookie("c2","c2");
		response.addCookie(cookie);
		response.addCookie(c2);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
}
