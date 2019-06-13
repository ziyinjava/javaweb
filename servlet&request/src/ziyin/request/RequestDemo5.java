package ziyin.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * @author ziyin
 @create 2019-05-2019/5/25-16:17
 */
@WebServlet("/requestDemo5")
public class RequestDemo5 extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		System.out.println(username);

		System.out.println("----------111--------");

		String[] hobbies = request.getParameterValues("hobby");
		for (String hobby : hobbies) {
			System.out.println(hobby);
		}

		System.out.println("----------222-------------");

		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {

			String name = parameterNames.nextElement();
			System.out.println(name);
			System.out.println(request.getParameter(name));  //如果有一个key对多个值要用getParameterValues
			System.out.println("---------------------");
		}

		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<String> keySet = parameterMap.keySet();
		for (String name : keySet) {
			String[] values = parameterMap.get(name);
			for (String value : values) {
				System.out.println(value);
			}
			System.out.println("------------");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
}
