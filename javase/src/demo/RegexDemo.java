package demo;/**
 * @author Fang WeiPing
 * @date 2020/5/18 18:31
 */

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

/**
 * @Description:
 * @Autho fangweiping
 * @Date: 2020/5/18 18:31
 */
public class RegexDemo {

    public static void main(String[] args) {

        //String  s =  "^SH.*$";//前缀匹配
        //String  s =  "^.*SH$";//后缀匹配
        String s = "^.*SH.*$";//包含SH

        boolean b = Pattern.matches(s, "333SH111");
        System.out.println(b);
    }


    @Test
    public  void test() {

    }
}

