package servlet.filter;

import javax.servlet.*;
import java.io.IOException;

public class Myfilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("my filter");

        //일종의 문지기라고 생각하면. 전처리

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
