package arithmetic.leetcode.all;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 */
public class Demo14 {
    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        String s = longestCommonPrefix(strs);
        System.out.println("s = " + s);
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String pre = "";
        for (int i = 0; i < strs.length; i++) {
            String tempPre;
            if (i + 1 <= strs[i].length()) {
                 tempPre= strs[i].substring(0, i + 1);
            }else {
                tempPre=strs[i];
            }
            for (int j = 0; j < strs.length; j++) {
                String substring;

//                if (!tempPre.equals(substring)) {
//                    return pre;
//                }
            }
            pre = tempPre;
        }
        return pre;
    }
}
