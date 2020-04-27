package arithmetic.leetcode.all;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 思路:回溯算法
 */
public class TelphoneNum {
    public static void main(String[] args) {
     /*   给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
        给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
        输入："23"
        输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].*/

        String str = "23";
        ArrayList  list = new ArrayList();
        String[] targetArr = new String[]{};

        HashMap<String, Character[]> map = new HashMap<String, Character[]>();
        for (int i = 0; i < 9; i++) {
            String num = i+2+"";
            Character[] words = new Character[3];
            for (int j = 0; j < 3; j++) {
                words[j] = (char) (i*3+97+j);
            }
            map.put(num, words);
        }
        System.out.println(list);
    }
}
