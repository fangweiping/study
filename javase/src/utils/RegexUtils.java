package utils;/**
 * @author Fang WeiPing
 * @date 2020/5/29 11:54
 */

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

/**
 * @Description:
 * @Autho fangweiping
 * @Date: 2020/5/29 11:54 
 */
public class RegexUtils {

    @Test
    public void regexTest() {
        //String  s =  "^SH.*$";//前缀匹配
        //String  s =  "^.*SH$";//后缀匹配
        String s = "^.*SH.*$";//包含SH

        boolean b = Pattern.matches(s, "333SH111");
        System.out.println(b);
    }
}
