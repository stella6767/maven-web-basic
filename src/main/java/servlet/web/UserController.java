package servlet.web;


import lombok.SneakyThrows;
import servlet.config.anno.Controller;
import servlet.config.anno.RequestMapping;
import servlet.domain.user.User;
import servlet.domain.user.dto.LoginDto;
import servlet.service.UserService;

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


    @RequestMapping(uri = "/user/joinform")
    public String joinForm() {

        return "user/joinform.jsp";
    }


    @RequestMapping(uri = "/user/save")
    public String join(LoginDto dto, HttpServletResponse response) {
        System.out.println("join 함수 요청됨");
        System.out.println(dto);

        System.out.println("!!!!1"  + response);
        return "/";
    }



    @RequestMapping(uri = "/user/login")
    public String login(LoginDto dto) {
        System.out.println("join 함수 요청됨");
        System.out.println(dto);
        return "/";
    }





    protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException, ClassNotFoundException {

        UserService userService = new UserService();

        String cmd = req.getParameter("cmd");
        HttpSession session = req.getSession();

        if (cmd == null){
            resp.sendRedirect("user/joinform.jsp");
        } else if(cmd.equals("login")){

            User principal = userService.login(new LoginDto(req.getParameter("name"), req.getParameter("password")));

            session.setAttribute("principal", principal);
            resp.sendRedirect("board?cmd=list");
        }else if(cmd.equals("save")){

            userService.join(new LoginDto(req.getParameter("name"), req.getParameter("password")));
            resp.sendRedirect("user/loginForm.jsp");
        }else if(cmd.equals("loginForm")){

            resp.sendRedirect("user/loginForm.jsp");
        }


    }
}
