package ziyin.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ziyin
 @create 2019-05-2019/5/25-14:26
 */
@WebServlet("/requestDemo1")
public class RequestDemo1 extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * 1. 获取请求方式 ：GET
	 * 					* String getMethod()
	 * 				2. (*)获取虚拟目录：/day14
	 * 					* String getContextPath()
	 * 				3. 获取Servlet路径: /demo1
	 * 					* String getServletPath()
	 * 				4. 获取get方式请求参数：name=zhangsan
	 * 					* String getQueryString()
	 * 				5. (*)获取请求URI：/day14/demo1
	 * 					* String getRequestURI():		/day14/demo1
	 * 					* StringBuffer getRequestURL()  :http://localhost/day14/demo1
	 *
	 * 					* URL:统一资源定位符 ： http://localhost/day14/demo1	中华人民共和国
	 * 					* URI：统一资源标识符 : /day14/demo1					共和国
	 *
	 * 				6. 获取协议及版本：HTTP/1.1
	 * 					* String getProtocol()
	 *
	 * 				7. 获取客户机的IP地址：
	 * 					* String getRemoteAddr()
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("-----");
		System.out.println(request.getMethod()); //GET
		System.out.println("-----");
		System.out.println(request.getContextPath());
		System.out.println("-----");
		System.out.println(request.getServletPath());  //  /requestDemo1
		System.out.println("-----");
		System.out.println(request.getQueryString()); //username=zhangsan&pwd=123
		System.out.println("-----");
		System.out.println(request.getRequestURI()); //  /requestDemo1
		System.out.println("-----");
		System.out.println(request.getRequestURL()); //  http://localhost:8080/requestDemo1
		System.out.println("-----");
		System.out.println(request.getProtocol());  //  HTTP/1.1
		System.out.println("-----");
		System.out.println(request.getRemoteAddr());  //0:0:0:0:0:0:0:1
		System.out.println("-----");

	}
}
