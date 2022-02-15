package servlet.config;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import servlet.service.BoardService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ControllerFactory {

    static Object instance;

    public static Object createController(Class cls){

        if (true){
            //요청이 올 때마다 매번 새로운 객체를 생성하기 때문에, 스프링의 controller를 잘 흉내냈다고 보기는 애매하다..

            try {
                System.out.println("controller instance 생성");
                instance = cls.getConstructor().newInstance();
                System.out.println(instance.getClass().getName());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return instance;
    }
}
