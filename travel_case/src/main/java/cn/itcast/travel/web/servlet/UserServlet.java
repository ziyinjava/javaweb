package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import cn.itcast.travel.util.JsonUtils;
import cn.itcast.travel.util.StatusEnum;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author ziyin
 @create 2019-06-2019/6/9-8:42
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();

    public void active(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String code = request.getParameter("code");
        boolean flag = userService.activeUser(code);
        String msg;
        if (flag) {
            msg = "激活成功, 请<a href='http://localhost/travel/login.html'>登录</a>";
        } else {
            msg = "激活失败,请联系管理员";
        }
        response.getWriter().write(msg);
    }

    public void register(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String check = request.getParameter("check");
        String checkcode = getCheckCode(request);
        if (checkcode == null || !checkcode.equalsIgnoreCase(check)) {
            writerObjectToJson(response,new ResultInfo(false,"验证码错误"));
            return;
        }

        User user = getUser(request);

        boolean flag = userService.regist(user);
        ResultInfo resultInfo = new ResultInfo();
        if (flag) {
            resultInfo.setFlag(true);
        } else {
            resultInfo.setFlag(false);
            resultInfo.setData("注册失败");
        }

        //将info对象序列化为json
        writerObjectToJson(response,resultInfo);
    }


    public void login(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String check = request.getParameter("check");
        String checkcode = getCheckCode(request);
        if (checkcode == null || !checkcode.equalsIgnoreCase(check)) {
            writerObjectToJson(response,new ResultInfo(false,"验证码错误"));
            return;
        }

        User user = getUser(request);

        User u = userService.login(user);

        ResultInfo resultInfo = new ResultInfo();
        if (u == null) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名或密码错误");
        } else if (!StatusEnum.Y.getStatus().equals(u.getStatus())) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("您还未激活,请先激活");
        } else {
            resultInfo.setFlag(true);
            request.getSession().setAttribute("user",u);
        }
        writerObjectToJson(response,resultInfo);
    }


    public void exit(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login.html");
    }


    public void findUser(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        writerObjectToJson(response,user);
    }


    private  String getCheckCode(HttpServletRequest request){
        HttpSession session = request.getSession();
        String checkcode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        return checkcode;
    }


    private User getUser(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return user;
    }
}
