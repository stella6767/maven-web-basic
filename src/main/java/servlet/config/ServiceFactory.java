package servlet.config;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import servlet.service.BoardService;
import servlet.service.UserService;

import java.lang.reflect.Method;

public class ServiceFactory {

    static BoardService boardService;
    static UserService userService;

    public static BoardService boardService(){

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BoardService.class);
        enhancer.setCallback(new InvocationHandler() {
            final BoardService boardService = new BoardService();
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

                long startTime = System.currentTimeMillis();
                Object invoke = method.invoke(boardService, objects);
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("실행시간 " + totalTime);
                return invoke;
            }
        });

        if (boardService == null){
            boardService = (BoardService) enhancer.create();
        }

        return boardService;
    }

    public static UserService userService(){

        if (userService == null){
            userService = new UserService();
        }

        return userService;
    }

}
