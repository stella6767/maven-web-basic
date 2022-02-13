package reflection;

public class TestClass {

    @Autowired //얘는 아무런 역할을 하지 않아요. 이 상태로는
    private Dog dog;

    public void test(){
        dog.bark();
    }

}
