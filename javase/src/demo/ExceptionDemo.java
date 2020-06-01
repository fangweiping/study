package demo;/**
 * @author Fang WeiPing
 * @date 2020/5/28 19:06
 */


/**
 * @Description:
 * @Autho fangweiping
 * @Date: 2020/5/28 19:06
 */
public class ExceptionDemo {


    public static void main(String[] args) {

        try {
            int a = 1;
            int i = a / 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        System.out.println("e = " + 1);
    }
}
