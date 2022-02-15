package servlet.web;

import lombok.SneakyThrows;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import servlet.config.ServiceFactory;
import servlet.config.anno.Controller;
import servlet.config.anno.RequestMapping;
import servlet.domain.board.Board;
import servlet.domain.board.dto.BoardReqDto;
import servlet.service.BoardService;
import servlet.utills.Script;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

@Controller
public class BoardController {

    private BoardService boardService = ServiceFactory.boardService();


    @RequestMapping(uri = "/detail")
    public String detail(){

        System.out.println("게시글 등록 페이지로 이동");

        return "board/detail.jsp";
    }


    @RequestMapping(uri = "/save")
    public String save(BoardReqDto dto){

        System.out.println("게시글 등록 " +  dto);

        return "board/list.jsp";
    }



    @RequestMapping(uri = "/findAll")
    public String findAll(HttpServletRequest req) throws SQLException, ClassNotFoundException {

        System.out.println("게시글 리스트");

        req.setAttribute("boards",
                boardService.findAll());


        return "board/list.jsp";
    }



}
