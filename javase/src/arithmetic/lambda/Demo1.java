package arithmetic.lambda;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 需求:
 * 获取相同年龄中成绩最高的学生
 */
public class Demo1 {


    public static void main(String[] args) {
        System.out.println("args = " + 4/3);
        ArrayList<User> list = new ArrayList<>();
        Map<Integer, List<User>> map = list.stream().sorted(((o1, o2) -> o2.getGrade() - o1.getGrade())).collect(Collectors.groupingBy(User::getAge));
        System.out.println(map);
        list.parallelStream().forEach(a ->{



        });

        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("nihao");
        Object o = threadLocal.get();
        System.out.println("o = " + o);


    }
}
