package lambda;

import arithmetic.leetcode.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 需求:
 * 获取相同年龄中成绩最高的学生
 */
public class Demo1 {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();

        list.add(new User(10, 88));
        list.add(new User(10, 34));
        list.add(new User(9, 22));
        list.add(new User(9, 32));
        list.add(new User(8, 44));
        list.add(new User(8, 11));

        Map<Integer, List<User>> map = list.stream().sorted(((o1, o2) -> o2.getGrade() - o1.getGrade())).collect(Collectors.groupingBy(User::getAge));
        System.out.println(map);
    }
}
