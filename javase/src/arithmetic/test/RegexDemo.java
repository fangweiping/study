package arithmetic.test;/**
 * @author Fang WeiPing
 * @date 2020/5/16 13:36
 */

import java.util.regex.Pattern;

/**
 * @Description:
 * @Autho fangweiping
 * @Date: 2020/5/16 13:36 
 */
public class RegexDemo {

    public static void main(String[] args) {

        String content = "SH34324243";

        String pattern = "^SH.*$";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(isMatch);



        String orderPrefix = "O";  //正常订单号前缀
        String historyOrderPrefix = "SH";  //历史订单号前缀

//        Pattern pattern= Pattern.compile("^.*"+historyOrderPrefix+".*$", Pattern.CASE_INSENSITIVE);



    }
}
