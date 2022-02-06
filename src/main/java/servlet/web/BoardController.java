package servlet.web;

import lombok.SneakyThrows;
import servlet.domain.user.User;
import servlet.service.BoardService;
import servlet.service.UserService;
import servlet.utills.Script;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet("/board")
public class BoardController extends HttpServlet {


    /**
     * 안내원 역할.
     *
     * 기본적으로 http는 stateless
     * 연결을 계속 유지하면 요청하는 사람이 많아질수록 서버의 부하가 증가하잖아요.
     * 단점은 동일한 클라이언트가 다시 요청을 할 때, 서버입장에서는 클라가 동일한 클라인지를 모름
     *
     *  로그인하고 끝날때까지 쓰는 정보는 session 영역에 넣어요.( 가급적 인증정보만)
     *  그러면 이런 의문점이 생기죠? 동일한 키 값인데 어떻게 클라들을 구별할 수 있나
     *
     *  세션영역은 클라이언트마다 따로 있는 게 아니라 하나가 있음.
     *  세션 저장소 안에 제이세션 ID 별로 클라를 구별해서 저장.
     *
     *  하나의 브라우저당 1개의 session 객체가 생성됨.
     *  즉, 같은 브라우저 내에서 요청되는 페이지들은 같은 객체를 공유하게 되는데, 이를 세션영역이라 함
     *  세션이 종료되면 객체는 반환됩니다. 30분
     *
     *  클라이언트 서버 요청-> 서버 브라우저에 임의 값을 부여 응답 -> 이후에 들어오는
     *  요청에 대해서 서버가 갖고 있는 브라우저의 정보와 비교를 해서 동일한 클라인지 판별
     *
     *  쿠키 vs 세션
     *  쿠키는 문자열 값만 쿠키 내에서 저장할 수 있거든요, 세션은 오브젝트를 담을 수 있음.
     *
     *
     *  web.xml
     *       * -ServletContext의 초기 파라미터
     *      * -Session의 유효시간 설정
     *      * -Servlet/JSP에 대한 정의
     *      * -Servlet/JSP 매핑
     *      * -Mime Type 매핑
     *      * -Welcome File list
     *      * -Error Pages 처리
     *      * -리스너/필터 설정
     *      * -보안
     */




    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);

    }


    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException, ClassNotFoundException {

        BoardService boardService = new BoardService();
        String cmd = req.getParameter("cmd");


        //하나의 클래스는 하나의 책임만. SRP
        //하나의 클래스나, 메서드에서 모든 로직을 처리하다보면, 재앙.
        //본인이 어떤 클래스나 메서드가 방대해진다.


        if (cmd == null){
            resp.sendRedirect("board/joinform.jsp");

        }else if (cmd.equals("save")){
            boardService.save(req.getParameter("title"), req.getParameter("content") );
            resp.sendRedirect("/board?cmd=list");

        }else if (cmd.equals("list")){

            System.out.println("???");

            req.setAttribute("boards",boardService.findAll());
            RequestDispatcher rd = req.getRequestDispatcher("board/list.jsp");
            rd.forward(req,resp);

        }else if (cmd.equals("ajaxSave")){
            Script.responseData(resp, boardService.ajaxSaveAndFindAll(req));
        }


    }
}
