package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author ziyin
 @create 2019-06-2019/6/9-8:37
 */
public class BaseServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
        try {
            Method method = this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void writerObjectToJson(HttpServletResponse response, Object obj){
        try {
            ObjectMapper om = new ObjectMapper();
            String str = om.writeValueAsString(obj);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(str);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
