package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 敏感词汇过滤器
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.创建代理对象，增强getParameter方法

        HttpServletRequest proxy_req = (HttpServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //增强getParameter方法
                //判断是否是getParameter方法
                if(method.getName().equals("getParameter")){
                    //增强返回值
                    //获取返回值
                    String value = (String) method.invoke(req,args);
                    if(value != null){
                        for (String str : list) {
                            if(value.contains(str)){
                                value = value.replaceAll(str,"***");
                            }
                        }
                    }
                    
                    return  value;
                }

                //判断方法名是否是 getParameterMap
				if(method.getName().equals("getParameterMap")){
					Map<String,String[]> map = (Map<String, String[]>) method.invoke(req);
					Set<Map.Entry<String, String[]>> entries = map.entrySet();

					Map<String,String[]> resultMap = entries.stream().collect(Collectors.toMap(e -> e.getKey(), e -> {
						 String[] values = e.getValue();
						return Stream.of(values).map(param -> {
							for (String s : list) {
								if (param.contains(s)) {
									return param.replaceAll(s, "***");
								}
							}
							return param;
						}).toArray(String[]::new);
					 }));

					return resultMap;
				}

                //判断方法名是否是 getParameterValues
				if(method.getName().equals("getParameterValues")){
					String[] values = (String[]) method.invoke(req,args);
					return Stream.of(values).map(param -> {
						for (String s : list) {
							if (param.contains(s)) {
								return param.replaceAll(s, "***");
							}
						}
						return param;
					}).toArray(String[]::new);
				}

                return method.invoke(req,args);
            }
        });

        //2.放行
        chain.doFilter(proxy_req, resp);
    }
    private List<String> list = new ArrayList<String>();//敏感词汇集合
    public void init(FilterConfig config) throws ServletException {
		System.out.println("---");

        try{
   /*         //1.获取文件真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
            //2.读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));*/

			InputStream is = this.getClass().getClassLoader().getResourceAsStream("敏感词汇.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("utf-8")));
			//3.将文件的每一行数据添加到list中
            String line = null;
            while((line = br.readLine())!=null){
                list.add(line);
            }

            br.close();

            System.out.println(list);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void destroy() {
    }

}
