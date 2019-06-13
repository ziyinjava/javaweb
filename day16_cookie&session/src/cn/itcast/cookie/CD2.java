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
 @create 2019-05-2019/5/27-15:20
 */
@WebServlet("/CD2")
public class CD2 extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		Cookie[] cookies = request.getCookies();
		if (cookies != null){
			for (Cookie cookie : cookies) {
				System.out.println();
				System.out.println(cookie.getName() + "-----" + cookie.getValue());
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
}
