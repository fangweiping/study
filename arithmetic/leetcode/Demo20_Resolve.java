package arithmetic.leetcode;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/**
 * 有效的括号
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * 示例 1:
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 */
public class Demo20_Resolve {
    public static void main(String[] args) {
        String str = "(";
        boolean valid = isValid(str);
        System.out.println("valid = " + valid);
    }

    /**
     * 栈存储方法来实现
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Map<String, String> map = new HashMap<>();
        map.put("(", ")");
        map.put("[", "]");
        map.put("{", "}");

        //使用栈空间来存储数据
        Stack<String> statck = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            if (map.containsKey(c)) {
                statck.push(c);
            } else {
                if (statck.empty()) {
                    return false;
                } else {
                    String topElement = statck.pop();
                    if (!c.equals(map.get(topElement))) {
                        return false;
                    }
                }
            }
        }
        return statck.empty();
    }
}
