package servlet.web;

import lombok.SneakyThrows;
import servlet.config.anno.Controller;
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

@Controller
public class BoardController {




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
