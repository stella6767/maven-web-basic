package servlet.filter;

import servlet.config.anno.Controller;
import servlet.config.anno.RequestMapping;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DispatherFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;


//        System.out.println("context path=>" + req.getContextPath());
//        System.out.println("식별자 주소=>" + req.getRequestURI());
//        System.out.println("전체주소=>" + req.getRequestURL());

        String endPoint = req.getRequestURI().replaceAll(req.getContextPath(), "");

        System.out.println("엔드포인트=>" + endPoint);

        List<Class> controllers = componentScan();

        for (Class controller : controllers) {

            Annotation[] annotations = controller.getAnnotations();

            for (Annotation annotation : annotations) {


                if (annotation instanceof Controller) {

                    try {
                        Object controllerInstance = controller.getConstructor().newInstance();
                        //System.out.println(controllerInstance);
                        Method[] methods = controller.getDeclaredMethods();

                        for (Method method : methods) {
                            //System.out.println(method);

                            RequestMapping requestMapping = method.getDeclaredAnnotation(RequestMapping.class);
                            if (requestMapping != null && requestMapping.uri().equals(endPoint)) {

                                Parameter[] params = method.getParameters();

                                String path = "";

                                if (params.length > 0) {


                                    Object[] objects = Arrays.stream(params).map((parameter) -> {

                                        Object dtoInstance = null;

                                        if (parameter.getType().equals(HttpServletResponse.class)) {
                                            return resp;
                                        } else {

                                            try {
                                                dtoInstance = parameter.getType().getConstructor(String.class, String.class).newInstance(req.getParameter("name"), req.getParameter("password"));
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            return dtoInstance;
                                        }

                                    }).toArray();

                                    path= (String) method.invoke(controllerInstance, objects);
                                }else{
                                    path= (String) method.invoke(controllerInstance);
                                }

                                System.out.println("path=>" + path);
                                RequestDispatcher rd = req.getRequestDispatcher(path);
                                rd.forward(req, resp);
                            }


                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

            }

        }


    }

    protected List<Class> componentScan() {

        List<Class> controllerList = new ArrayList<>();

        String packageName = "servlet.web";
        String packageNameSlash = "./" + packageName.replace(".", "/");
        URL directoryUrl = Thread.currentThread().getContextClassLoader().getResource(packageNameSlash);

        if (directoryUrl == null) {
            System.err.println("url resource를 얻지 못했습니다..");
        }

        String directoryString = directoryUrl.getFile();

        if (directoryString == null) {
            System.err.println("url resource를 얻지 못했습니다..");
        }

        File dierctoryFile = new File(directoryString);


        if (dierctoryFile.exists()) {

            String[] files = dierctoryFile.list();

            for (String file : files) {

                if (file.endsWith(".class")) {
                    file = file.replace(".class", "");

                    try {
                        Class<?> temp = Class.forName(packageName + "." + file);
                        controllerList.add(temp);

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }

            }

        }

        return controllerList;
    }


}
