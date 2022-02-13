package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class DiService {


    public static <T> T getObject(Class<T> classType){

        T instance = createInstance(classType);

        Field[] declaredFields = instance.getClass().getDeclaredFields();

        for (Field declaredField : declaredFields) {

            Autowired annotation = declaredField.getAnnotation(Autowired.class);

            if(annotation != null){
                //System.out.println("check => " + annotation);
                Object instance1 = createInstance(declaredField.getType());
                declaredField.setAccessible(true);

                try {
                    declaredField.set(instance, instance1);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException("빈 주입에 실패!!");
                }

            }
        }

        return instance;
    }

    private static <T> T createInstance(Class<T> classType) {

        try {
            return classType.getConstructor(null).newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("빈 생성 오류");
        }
    }

}
