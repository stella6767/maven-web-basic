package proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class TestProxy {

    @Test
    void originalProxyPattern() {

        /**
         * 오리지날 프록시 패턴
         */

        //Animal animal = new DogProxy(new Dog());
        //animal.bark();
    }

    @Test
    public void reflectDynamicProxy() {

//        Animal dog = (Animal) Proxy.newProxyInstance(Animal.class.getClassLoader(), new Class[]{Animal.class}, new InvocationHandler() {
//            final Animal animal = new Dog();
//
//            @Override
//            public Object invoke(Object servlet.proxy, Method method, Object[] args) throws Throwable {
//
//                System.out.println("dynamic servlet.proxy start");
//                method.invoke(animal, args);
//                System.out.println("dynamic servlet.proxy end");
//
//                return null;
//            }
//        });
//
//        dog.bark();

    }





    @Test
    public void cglibProxyTest(){

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dog.class);

        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

                Dog dog = new Dog();
                System.out.println("cglib servlet.proxy start");
                Object invoke = method.invoke(dog);
                System.out.println(dog.retrunName());
                System.out.println("cglib servlet.proxy end");

                return invoke;
            }
        });


        Dog dog = (Dog) enhancer.create();
        dog.bark();

//        enhancer.setCallback(new FixedValue() {
//            @Override
//            public Object loadObject() throws Exception {
//                return "점박이";
//            }
//        });
//
//        Dog dog = (Dog) enhancer.create();
//        System.out.println(dog.retrunName());

    }


//    @Test
//    public void byteBuddyProxyTest() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//
//        Class<? extends Dog> dogProxy = new ByteBuddy().subclass(Dog.class)
//                .method(named("bark"))
//                .intercept(InvocationHandlerAdapter.of(new java.lang.reflect.InvocationHandler() {
//
//                    final Dog dog = new Dog();
//
//                    @Override
//                    public Object invoke(Object servlet.proxy, Method method, Object[] args) throws Throwable {
//                        System.out.println("byteBuddy Dynamic servlet.proxy start");
//                        Object invoke = method.invoke(dog);
//                        System.out.println("byteBuddy Dynamic servlet.proxy end");
//                        return invoke;
//                    }
//                }))
//                .make()
//                .load(Dog.class.getClassLoader())
//                .getLoaded();
//
//        Dog dog = dogProxy.getConstructor().newInstance();
//
//        dog.bark();
//    }



    public static class Dog {

        public void bark() {
            System.out.println("멍멍");
        }

        public String retrunName() {

            return "얼룩이";
        }
    }


//    public static class Dog implements Animal {
//
//        @Override
//        public void bark() {
//            System.out.println("멍멍");
//        }
//    }
//
//    public interface Animal {
//
//        void bark();
//    }


//    public static class DogProxy implements Animal {
//        private Dog dog;
//
//        public DogProxy(Dog dog) {
//            this.dog = dog;
//        }
//
//        @Override
//        public void bark() {
//
//            System.out.println("servlet.proxy object start");
//            dog.bark();
//            System.out.println("servlet.proxy object end");
//
//        }
//    }


}
