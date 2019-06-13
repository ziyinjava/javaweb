package cn.itcast.web.download;

import cn.itcast.web.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//获取文件名
		String filename = request.getParameter("filename");
		ServletContext servletContext = this.getServletContext();
		String realPath = servletContext.getRealPath("/img/" + filename);

		response.setHeader("content-type", servletContext.getMimeType(filename));
		response.setHeader("content-disposition",
				"attachment;filename=" + DownLoadUtils.getFileName(request.getHeader("user-agent"), filename));
		try (FileInputStream fis = new FileInputStream(realPath)) {
			byte[] bytes = new byte[1024];
			fis.read(bytes);
			ServletOutputStream outputStream = response.getOutputStream();
			int len = 0;
			while ((len = fis.read(bytes)) != -1) {
				outputStream.write(bytes, 0, len);
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
}
