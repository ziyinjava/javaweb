package cn.itcast.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@WebServlet("/testServlet")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String msg = request.getParameter("msg");
		System.out.println(name+":"+msg);

		System.out.println("------------------------------");

		Map<String, String[]> map = request.getParameterMap();
		Set<Map.Entry<String, String[]>> entries = map.entrySet();
		for (Map.Entry<String, String[]> entry : entries) {
			System.out.println(entry.getKey() + "---" + Arrays.toString(entry.getValue()));
		}

		System.out.println("------------------------------");

		String[] names = request.getParameterValues("name");
		System.out.println(Arrays.toString(names));
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("初始化");
		super.init(config);
	}
}
