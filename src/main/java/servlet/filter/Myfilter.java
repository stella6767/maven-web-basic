package servlet.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Myfilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("my filter");

        //일종의 문지기라고 생각하면. 전처리
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        RequestDispatcher rd =req.getRequestDispatcher("/user/joinForm.jsp");
        rd.forward(req, resp);

        //RequestDispatcher 는 필터를 다시 안 타요.

        //filterChain.doFilter(servletRequest, servletResponse);
    }
}
