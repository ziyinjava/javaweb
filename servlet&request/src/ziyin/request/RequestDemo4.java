package ziyin.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author ziyin
 @create 2019-05-2019/5/25-16:17
 */
@WebServlet("/requestDemo4")
public class RequestDemo4 extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		String line  = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line); //username=zhangsan&pwd=123  post请求体参数跟get一样放在一行里
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
