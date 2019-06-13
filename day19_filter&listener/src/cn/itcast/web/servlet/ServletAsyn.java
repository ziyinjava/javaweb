package cn.itcast.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ServletAsyn", urlPatterns = "/ServletAsyn", asyncSupported = true)
public class ServletAsyn extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAsyn() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());

        System.out.println("Servlet执行开始时间："+new Date());
          response.setCharacterEncoding("utf-8");
          response.setContentType("text/html;charset=utf-8");
          PrintWriter out = response.getWriter();
          Date date = new Date(System.currentTimeMillis());
          SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
          out.print("<h1>");
          out.println("Servlet  begin --" + sdf.format(date) + "<br>");// 响应输出到客户端
          out.print("</h1>");
          out.print("<hr>");

        /*
         * 体现异步思想，Servlet执行结束，而异步的线程仍然在继续跑
         */
        AsyncContext context=request.startAsync();
        new Thread(new Executor(context)).start(); //new Thread 传递AsyncContext对象   并且start
        System.out.println("Servlet执行结束时间："+new Date());
    }

    //内部类实现线程
    public class Executor implements Runnable{
        private AsyncContext context;
        public Executor(AsyncContext context){
            this.context=context;
        }

        @Override
        public void run() {
            //执行相关复杂业务
            try {
                Thread.sleep(1000*3);
                context.getRequest();
                context.getResponse();
                 ServletResponse response = context.getResponse();
                    PrintWriter out = response.getWriter();
                    Date date = new Date(System.currentTimeMillis());
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

				out.print("<h1>");
				out.println("Thread worker finished --"+ sdf.format(date));// 响应输出到客户端
				out.print("</h1><hr>");

				System.out.println("业务完成时间:"+new Date());
				ServletRequest request = context.getRequest();
				context.dispatch("/index.jsp");
			//	out.flush();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}