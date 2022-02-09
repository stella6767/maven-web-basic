package servlet.config;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import servlet.domain.board.BoardDao;
import servlet.service.BoardService;

import java.lang.reflect.Method;

public class ServiceFactory {

    //https://incheol-jung.gitbook.io/docs/study/tobys-spring/undefined/6-aop

    static BoardService boardService;

    public static BoardService boardService(){

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BoardService.class);
        enhancer.setCallback(new InvocationHandler() {
            final BoardService boardService = new BoardService();
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

                long startTime = System.currentTimeMillis();

                System.out.println("method: " + boardService);
                Object invoke = method.invoke(boardService, objects);

                Thread.sleep(5000);

                long endtime = System.currentTimeMillis();
                long totalTime = endtime - startTime;
                System.out.println("실행 시간 " + totalTime/1000);
                return invoke;
            }
        });


 //       enhancer.setCallback(new );

        if (boardService == null){
            boardService = (BoardService) enhancer.create();
        }

//        boardService = (BoardService) enhancer.create();

        return boardService;
    }

}
