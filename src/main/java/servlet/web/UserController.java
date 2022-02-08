package servlet.web;


import com.google.gson.Gson;
import lombok.SneakyThrows;
import servlet.config.anno.Controller;
import servlet.config.anno.RequestMapping;
import servlet.domain.user.User;
import servlet.domain.user.dto.LoginDto;
import servlet.service.UserService;
import servlet.utills.Script;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class UserController {


    @RequestMapping(uri = "/user")
    public String joinForm() {

        return "user/joinform.jsp";
    }

    @RequestMapping(uri = "/user/join")
    public String join(LoginDto dto,HttpServletResponse response) throws IOException {
        System.out.println("join 함수 요청됨");
        System.out.println(dto);

        System.out.println("!!!!1"  + response);

        try {
            new UserService().join(dto);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Script.responseData(response, "성공응답!!");
        return "loginForm.jsp";
    }


    @RequestMapping(uri = "/user/loginForm")
    public String loginForm(){

        return "loginForm.jsp";
    }


    @RequestMapping(uri = "/user/login")
    public void login(LoginDto dto, HttpServletResponse resp) throws IOException {
        System.out.println("login 함수 요청됨");
        System.out.println(dto);

        Gson gson = new Gson();

        String user = "";

        try {
            User principal = new UserService().login(dto);

            user = gson.toJson(principal);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Script.responseData(resp, user);
    }


//    @RequestMapping(uri = "/user/login")
//    public String login(LoginDto dto, HttpServletRequest req) throws IOException {
//        System.out.println("login 함수 요청됨");
//        System.out.println(dto);
//
//        try {
//            User principal = new UserService().login(dto);
//            req.setAttribute("principal", principal);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//
//        return "board/list";
//    }


    @RequestMapping(uri = "/board/list")
    public String moveBoardList(){

        return "board/list.jsp";
    }


    protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException, ClassNotFoundException {

//        String cmd = req.getParameter("cmd");
//        HttpSession session = req.getSession();
//
//        if (cmd == null){
//            resp.sendRedirect("user/joinform.jsp");
//        } else if(cmd.equals("login")){
//
//            User principal = userService.login(new LoginDto(req.getParameter("name"), req.getParameter("password")));
//
//            session.setAttribute("principal", principal);
//            resp.sendRedirect("board?cmd=list");
//        }else if(cmd.equals("save")){
//
//            userService.join(new LoginDto(req.getParameter("name"), req.getParameter("password")));
//            resp.sendRedirect("user/loginForm.jsp");
//        }else if(cmd.equals("loginForm")){
//
//            resp.sendRedirect("user/loginForm.jsp");
//        }


    }
}
