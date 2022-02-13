package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainClass {

    /**
     * 리플렉션 API 에 대해서 알아볼게요.
     * 구체적인 클래스 타입을 알지 못해도 메타데이터를 통해서, 클래스의 정보에 접근할 수 있게 해주는 API 에요. (런타임시에)
     *
     * @param args
     */



    public static void main(String[] args) {

        //객체 vs 클래스 vs 인스턴스

        //Object dog = new Dog();

        //dog.; // 불가능.. 자바는 컴파일러를 사용하는데, 컴파일 타임에 타입이 결정돼요.

        /**
         * 구체적인 클래스 타입을 몰라도 정보를 접근할 수 있는 방법이 크게 3가지가 있어요,
         *
         * 리플렉션 API를 이용해서 클래스의 모든 정보에 접근할 수가 있어요.
         * 필드 접근가능
         * 메서드 접근가능
         * 상위 클래스 가져올 수 있고
         * 인터페이스도 접근가능
         * 어노테이션도 접근가능
         * 생성자도 접근가능합니다.
         *
         * 간단하게 리플렉션 API 에 대해서 알아봤어요.
         *
         */


        //TestClass testClass = new TestClass();
        //testClass.test();


        TestClass object = DiService.getObject(TestClass.class);
        object.test();

        //리플렉션 + 프록시 패턴을 이용해서,


        try {

            Class<Dog> dogClass = Dog.class; //1번째 방법

            Dog dog = new Dog();
            Class<? extends Dog> aClass = dog.getClass(); //2번째 방법
            //Class<? extends TestClass> test = testClass.getClass(); //2번째 방법

            Class<?> aClass1 = Class.forName("Dog"); //3번째 방법


            Field[] declaredFields = dogClass.getDeclaredFields();

            for (Field declaredField : declaredFields) {
                System.out.println("field=>" + declaredField);

            }

            Method[] declaredMethods = dogClass.getDeclaredMethods();

            for (Method declaredMethod : declaredMethods) {
                System.out.println("method=>" + declaredMethod);
            }

            Method bark = dogClass.getDeclaredMethod("bark");
            Method bark2 = dogClass.getDeclaredMethod("bark2", String.class);
            Method sleep = dogClass.getDeclaredMethod("sleep");

            bark.invoke(dog);
            bark2.invoke(dog, "왈왈");
            sleep.setAccessible(true);
            sleep.invoke(dog);

            /**
             * 은닉성과 캡슐화 뭐 이런 거 배우잖아요.
             *
             */


            Class<? super Dog> superclass = dogClass.getSuperclass();

            System.out.println("부모 클래스=>" + superclass);

            Class<?>[] interfaces = dogClass.getInterfaces();

            for (Class<?> anInterface : interfaces) {
                System.out.println("interface=>" + anInterface);

            }

            Constructor<?>[] constructors = dogClass.getConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println("생성자=>" + constructor);
            }



//            Annotation[] annotations = test.getAnnotations();

            Field[] declaredFields1 = dogClass.getDeclaredFields();

            for (Field field : declaredFields1) {
                Autowired annotation = field.getAnnotation(Autowired.class);

                System.out.println("runtime 시 anotation 정보 읽어오기=>" + annotation);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
