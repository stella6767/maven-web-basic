package servlet.web;

import com.google.gson.Gson;
import servlet.domain.Board;
import servlet.service.BoardService;
import servlet.utills.Script;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/board")
public class BoardController extends HttpServlet {


    /**
     * 안내원 역할.
     *
     */


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("get 요청 옴");
        doProcess(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //System.out.println("post 요청 옴=>" + req.getParameter("title"));
        doProcess(req, resp);
    }

    protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        Gson gson = new Gson();


        resp.setContentType("text/html; charset=utf-8");

        BoardService boardService = new BoardService();

        String cmd = req.getParameter("cmd");


        if (cmd != null && cmd.equals("save")){

            String title = req.getParameter("title");
            String content = req.getParameter("content");

            System.out.println("title  " + title + "content " + content );

            Board board = Board.builder()
                    .title(title)
                    .content(content)
                    .build();
            boardService.save(board);

            resp.sendRedirect("/board?cmd=list");


        }else if (cmd != null && cmd.equals("list")){


            List<Board> boards = boardService.findAll();

            System.out.println("boards=>" + boards);
            req.setAttribute("boards", boards);

            RequestDispatcher rd = req.getRequestDispatcher("board/list.jsp");
            rd.forward(req,resp);


        }else if (cmd != null && cmd.equals("ajaxSave")){

//            BufferedReader br = req.getReader();
//            System.out.println("data " + br.readLine());
            String jsonBody = Script.getBody(req);
            System.out.println("jsonBody=>" + jsonBody);
            Board board = gson.fromJson(jsonBody, Board.class);
            System.out.println("자바 오브젝트로 역직렬화 =>" + board);
            boardService.save(board);

            List<Board> boards = boardService.findAll();
            String jsonBoards = gson.toJson(boards);
            Script.responseData(resp, jsonBoards);

        }




        else{
            resp.sendRedirect("board/detail.jsp");
        }


    }
}
