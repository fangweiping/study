package leetcode;


/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 */
public class Demo7_Resovle {
    public static void main(String[] args) {
        int x =  -901000;
        int reverse = reverse(x);
        System.out.println("reverse = " + reverse);
    }
    public static int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        String num = String.valueOf(x);
        for (int length = num.length()-1; length >=0; length--) {
            sb.append(num.charAt(length));
        }
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '0' ) {
                sb.deleteCharAt(i);
                i--;//删除后,索引发生变化,对应自减1
            }else {
                break;
            }
        }
        try {
            if (x < 0) {
                sb.deleteCharAt(sb.length() - 1);
                return Integer.valueOf('-'+sb.toString());
            }
            return Integer.valueOf(sb.toString());
        } catch (NumberFormatException e) {
            //格式化异常, 说明数值超出 int取值范围
           return 0;
        }
    }
}
