package reflection;

public class Dog extends Mammel implements Animal{


    @Autowired
    private String name;

    private int age;

    public void bark(){
        System.out.println("멍멍");
    }

    public void bark2(String word){
        System.out.println(word);
    }


    private void sleep(){
        System.out.println("쿨쿨 zzzzz");
    }


}
