package servlet.web;

import lombok.extern.slf4j.Slf4j;
import servlet.utills.Script;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/test")
public class TestController extends HttpServlet {


    private int num = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("get 요청 옴");
        doProcess(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("post 요청 옴=>" + req.getParameter("title"));
        doProcess(req, resp);
    }


    /**
     *  이게 서블릿이에요.
     *  자바 코드 안에 html이 혼재되어 있는 것.
     *  was(톰캣) 의 역할은 일종의 컴파일러 역할을 해줌.
     *  웹 브라우저는 자바코드를 해석하지 못해요. 자원이 동적으로 해석이 되는 거에요.
     *  서블릿에 클라이언트 요청을 전달하고, 서블릿이 요청을 처리한 결과를 응답으로 생성한다음 그걸 웹 브라우저에 전송
     *
     *  1. 톰캣은 멀티스레드 컨테이너이다. 이클립스와는 다르게 인텔리제이는 메타 server.xml 파일을 제공하지 않거든요.
     * thread pool 방식이란, 기본적으로 자바의 서블릿은 스레드 pool을 이용.. 클라이언트의 요청이 있을 때마다 스레드를 만들거든요.
     * request 요청 하나당 thread 하나 할당해줘가지고 그게 servlet request, response 객체로 만들어줘가지고, 그렇게 쓰는 걸로 알고 있는데.
     * 서블릿 버전에 따라 방식이 바꼈다고는 들었는데, 일단은 잘 모르니까 옛날 방식으로 설명을 드릴게요.
     *
     * 스레드 개수의 max 값이 정해져있음. 서버의 사양에 따라서 정하면 되겠져?
     * 생성한 스레드를 클라이언트의 사용이 끝나면. 그 쓰레드는 사라지지 않고 남아있어요.
     * 다른 클라이언트가 이미 있던 그 스레즈를 재활용합니다.
     *
     * 웹 서버가 요청이 동시에 여러개 올 수 있잖아요. 그러면 진짜 옜날에는 요청 당 프로세스 하나 할당해줘가지고, 작업을 해서 비동기 방식으로 해결을 했는데..
     * 프로세스 하나에 요청 당 스레드를 생성해서, 작업을 비동기적으로 진행해나가는 게 더 좋단 말이에요.
     * 동일한 프로그램에 대해서는 하나의 프로세스를 생성한 후 여러개의 스레드 방식으로 요청이 처리되는 방식이 확장 CGI
     * ex) ASP, PHP, Servlet, JSP
     *
     * 스레드 1000 개 면 1000명이 딱 동시에 요청을 하면, 10001명째 사람이 그 순간에 요청이 오면 블락킹이 걸리겠지만.
     *
     * servlet 를 쓰면서 얻을 수 있는 장점은 크게 2가지가 있겠죠.
     * 하나는 동적인 데이터(자바코드)를 컴파일해서 브라우저에 응답시켜줄 수 있다.
     * 두번째는 http 프로토콜은 기본적으로 응용계층 프로토콜이라, 정보의 양이 꽤 있어요.
     *
     *
     * 톰캣은 필터를 제공한다.
     *
     *
     */

    protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

//        System.out.println("req=> " + req.getRequestURI());
//
//        System.out.println(req.getMethod());
//
//        resp.setContentType("text/html; charset=utf-8");
//
//        PrintWriter out = resp.getWriter();
//
//        out.println("<html><body>");
//        out.println("<h1>현재 시각은</h1>");
//        out.println(new Date());
//        out.println("</body></html>");
        
        String cmd = req.getParameter("cmd");
        System.out.println("cmd=>" + cmd);

        /**
         * http 통신에는 헤더와 바디가 존재하는데, 클라이언트가 서버에게 데이터를 요청할 때, 서버는
         * 그 데이터를 바이트스트림으로 직렬화해서 전달해줘야 되거든요. 그러면 클라이언트는 그 데이터를 역직렬화합니다.
         * 만약에 데이터가 avi 파일이면, 헤더는 그 데이터가 avi 파일이라는 정보를 담고 있어야 돼요.
         * 바디는 그 직렬화된 데이터를 담고있어야 되요. 클라리언트는 헤더를 분석해서 avi 파일에 맞게 역직렬화하면 되고요.
         * 즉 헤더로 바디를 분석할 수 있다.
         *
         * 웹에서 데이터를 통신할 때, 주고받을 수 있는 역역이 크게 3가지 있음
         * 1. url, (get 방식, 보안 취약, 캐싱 가능.. 주로 조회용도(select)
         * 2. 헤더영역
         * 3. 바디영역( post 방식, 좀 더 보안에 안전, 캐싱 불가)
         *
         *
         *
         */


        if (cmd == null){
            req.setAttribute("date", new Date());

            RequestDispatcher rd = req.getRequestDispatcher("test/test.jsp");
            rd.forward(req, resp);

            //uri  자원 식별자 접근방법. url urn
            //resp.sendRedirect("board/test.jsp");
        }else if(cmd.equals("board")){

            //resp.sendRedirect("board/joinform.jsp");

            //프론트 컨트롤러 패턴.
            //라우팅 하는 역할을 담당하는( 앞단에서) 클래스를 만든 거에요. Controller.
            // MVC model C ==== 이거는 자세한 얘기는


        }else if (cmd.equals("add")){

            num ++;

            Script.responseData(resp, String.valueOf(num));

//            req.setAttribute("num", String.valueOf(num));
//            req.setAttribute("date", new Date());
//
//            RequestDispatcher rd = req.getRequestDispatcher("/test/test.jsp");
//
//            rd.forward(req, resp);



        }


        /**
         * 이게 딱 보기에도 거지같잖아요.
         * JSP = java server page. html 코드 안에 자바 코드를 혼재하는 게 더 편하다..
         */




    }





}
