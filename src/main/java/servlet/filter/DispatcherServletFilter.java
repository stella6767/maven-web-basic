package servlet.filter;

import servlet.config.anno.Controller;
import servlet.config.anno.RequestMapping;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class DispatcherServletFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        System.out.println("컨텍스트패스 : " + req.getContextPath()); // 프로젝트 시작주소
        System.out.println("식별자주소 : " + req.getRequestURI()); // 끝주소
        System.out.println("전체주소 : " + req.getRequestURL()); // 전체주소

        String endPoint = req.getRequestURI().replaceAll(req.getContextPath(), "");
        System.out.println("엔드포인트 : " + endPoint);


        List<Class> controllers = componentScan();

        for (Class controller : controllers) {
            Annotation[] annotations = controller.getAnnotations();

            for (Annotation annotation : annotations) {

                System.out.println(annotation);

                if (annotation instanceof Controller){

                    try {
                        Object controllerInstance = controller.getConstructor().newInstance();
                        Method[] methods = controller.getDeclaredMethods();

                        for (Method method : methods) {

                            //System.out.println(method);
                            RequestMapping requestMapping = method.getDeclaredAnnotation(RequestMapping.class);
                            //System.out.println("requestMapping " + requestMapping);

                            if (requestMapping != null && requestMapping.uri().equals(endPoint) ){

                                System.out.println("uri " + requestMapping.uri());
                                System.out.println("method " + method);
                                System.out.println("리플렉션 컨트롤러 함수 어노테이션 값: " + requestMapping.uri());


                                try {

                                    Parameter[] params = method.getParameters();
//                                for (Parameter param : params) {
//                                    System.out.println("parame" + param);
//                                }
                                    String path;

                                    if (params.length > 0){
                                        //loginDto

                                        Object parameInstance= null;

                                        Object[] parameters = {null, null};

                                        int i = 0;

                                        for (Parameter param : params) {

                                            if (param.getType().equals(HttpServletResponse.class)){
                                                setData(resp, req);

                                                //parameInstance= resp;

                                                parameters[i] = resp;
                                            }else{
                                                parameInstance = param.getType().getConstructor(String.class, String.class)
                                                        .newInstance(req.getParameter("name"), req.getParameter("password"));
                                                System.out.println("parameInstance " + parameInstance);

                                                setData(parameInstance, req);

                                                parameters[i] = parameInstance;
                                            }
                                            i++;

                                        }
                                        path = (String) method.invoke(controllerInstance, parameters);

                                    }else {
                                        path = (String) method.invoke(controllerInstance);

                                    }


                                    System.out.println("path : " + path);
                                    RequestDispatcher dis = req.getRequestDispatcher(path);
                                    dis.forward(req, resp);
                                    //requestDispatch는 필터를 다시 안탄다.!!!
                                    //requestdispather는 내부에서 실행되므로 필터를 안 탄다.

                                    break; // 더 이상 메서드를 리플렉션 할 필요 없어서 빠져나감.


                                }catch (Exception e){
                                    e.printStackTrace();
                                }


                            }

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

            }

        }

    }


    private String keyToMethodKey(String key) {
        String firstkey = key.substring(0, 1);
        String remainKey = key.substring(1);
        return "set" + firstkey.toUpperCase() + remainKey;
    }


    private <T> void setData(T instance, HttpServletRequest request) {
        // 파라미터의 key 값을 받아온 후 이를 변형 해줘야 한다.
        System.out.println("Instance Type : " + instance.getClass());
        Enumeration<String> keys = request.getParameterNames();
        while(keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String methodKey = keyToMethodKey(key);
            System.out.println("Setter Method : "  + methodKey);

            // 리플렉션을 이용해 변형된 key 값과 메소드를 비교할 수 있다.
            Method[] methods = instance.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if(method.getName().equals(methodKey)) {
                    try {

                        System.out.println("???? " +request.getParameter(key));
                        method.invoke(instance, request.getParameter(key));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



    public List<Class> componentScan() {
        List<Class> controllerList = new ArrayList<>();
        String packageName = "servlet.web";
        String packageNameSlash = "./" + packageName.replace(".", "/");

        System.out.println("1  " + packageNameSlash);

        URL directoryURL = Thread.currentThread().getContextClassLoader().getResource(packageNameSlash);

        System.out.println("2  " + directoryURL);

        if (directoryURL == null) {
            System.err.println("Could not retrive URL resource : "+ packageNameSlash);
        }

        String directoryString = directoryURL.getFile();

        System.out.println("3  " + directoryString);

        if (directoryString == null) {
            System.err.println("Could not find directory for URL resource : "+ packageNameSlash);
        }

        File directory = new File(directoryString);
        if (directory.exists()) {

            String[] files = directory.list();
            for (String fileName : files) {
                if (fileName.endsWith(".class")) {
                    fileName = fileName.replace(".class", "");
                    try {
                        Class temp = Class.forName(packageName + '.' + fileName);

                        System.out.println(temp);
                        controllerList.add(temp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return controllerList;
    }
}
