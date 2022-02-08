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



    /**
     * 프론트 컨트롤러 패턴이란, 앞단에서 서블릿 하나가 요청을 받아가지고,
     * 요청에 따라서 각기 다른 비즈니스 로직으로 전파를 시키고 응답을 하는 패턴.
     *
     * 유저 도메인에 대한 어떤 요청들만 라우팅하는 역할을 수행만 하면 되죠.
     *
     */

    UserService userService = new UserService();


    @RequestMapping(uri = "/user")
    public String joinForm(){

        System.out.println("회원가입 페이지로 이동");

        return "user/joinForm.jsp";
    }


    @RequestMapping(uri = "/join")
    public String join(LoginDto dto) throws SQLException, ClassNotFoundException {

        System.out.println("join 메서드 호출!");
        System.out.println(dto);

        userService.join(dto);

        return "user/loginForm.jsp";
    }


    @RequestMapping(uri = "/login")
    public void login(LoginDto dto, HttpServletResponse resp) throws SQLException, ClassNotFoundException, IOException {
        System.out.println("login 메서드 호출!");
        System.out.println(dto);

        Gson gson = new Gson();
        User principal = userService.login(dto);
        Script.responseData(resp, gson.toJson(principal));
    }



    @RequestMapping(uri = "/loginForm")
    public String loginForm() {
        return "user/loginForm.jsp";
    }




//
//    protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException, ClassNotFoundException {
//
//        UserService userService = new UserService();
//
//        String cmd = req.getParameter("cmd");
//        HttpSession session = req.getSession();
//
//        Gson gson = new Gson();
//
//        if (cmd == null){
//            resp.sendRedirect("user/joinForm.jsp");
//        } else if(cmd.equals("login")){
//
//            User principal = userService.login(new LoginDto(req.getParameter("name"), req.getParameter("password")));
//            Script.responseData(resp, gson.toJson(principal));
//
//        }else if(cmd.equals("save")){
//
//            userService.join(new LoginDto(req.getParameter("name"), req.getParameter("password")));
//            resp.sendRedirect("user/loginForm.jsp");
//        }else if(cmd.equals("loginForm")){
//
//            resp.sendRedirect("user/loginForm.jsp");
//        }
//
//
//    }
}
