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
//        enhancer.setCallback(new InvocationHandler() {
//            final BoardService boardService = new BoardService();
//            @Override
//            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
//
//                long startTime = System.nanoTime();
//
//                System.out.println("method: " + boardService);
//                Object invoke = method.invoke(boardService, objects);
//                long endtime = System.nanoTime();
//                long elapsedTime = startTime - endtime;
//                System.out.println(elapsedTime + " ns");
//                return invoke;
//            }
//        });


 //       enhancer.setCallback(new );

        if (boardService == null){
            boardService = (BoardService) enhancer.create();
        }

//        boardService = (BoardService) enhancer.create();

        return boardService;
    }

}
