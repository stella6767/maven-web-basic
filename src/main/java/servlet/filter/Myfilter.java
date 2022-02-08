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

//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//
//        RequestDispatcher dis = req.getRequestDispatcher("board/list.jsp");
//        dis.forward(req, resp);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
