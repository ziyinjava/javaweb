package ziyin.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ziyin
 @create 2019-05-2019/5/25-15:12
 */
@WebServlet("/requestDemo3")
public class RequestDemo3 extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String header = request.getHeader("user-agent");
//		if (header.contains("Chrome")){
//			System.out.println("谷歌来了");
//		}

		String referer = request.getHeader("referer");  //通过浏览器直接输入地址访问, referer为null
		System.out.println(referer);

		if (referer != null) {
			if (referer.contains("/day14")) {
				System.out.println("正常播放");
			} else {
				System.out.println("想看电影吗, 上day14把");
			}
		}
	}
}
