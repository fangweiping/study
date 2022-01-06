package arithmetic.test;


public class StaticTest {
    static  int i =1;
    static void show() {
        System.out.println("静态");
    }

    public static void main(String[] args) {
        //F-02
        long a = 0L;
        System.out.println(a=0);


       int c =333;

       Long l = (long)c;
        System.out.println("l = " + l);

    }
}
