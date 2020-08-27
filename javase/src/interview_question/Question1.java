package interview_question;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * //评测题目: 无
 * <p>
 * 淘宝账号系统：
 * user D（uuid=124,email=122.126.com,tel=134555555555)
 * user A（uuid=121,email=121.126.com,tel=13333333333)
 * user B（uuid=122,email=122.126.com,tel=13333333333)
 * user C（uuid=123,qq=123456,tel=13333333333)
 * user E（uuid=125,email=125.126.com,qq=123456)
 * user F（uuid=126,email=126.126.com,qq=66666)
 * user G（uuid=127,email=127.126.com,qq=777777)
 * <p>
 * <p>
 * /email/tel/qq相同算一个对象
 * <p>
 * <p>
 * List(User)          List(ABCDEFG)
 * List(List(User())   List(List(ABCDE),List(F),List(G))
 * <p>
 * public class User {
 * <p>
 * long uuid;
 * String email;
 * String tel;
 * String qq;
 * }
 */

public class Question1 {
    public static void main(String[] args) {
    }


    @Test
    public void groupByTest1() {
        List<Question1_User> users = getUser();
        List<List<String>> list = users.stream().map(user -> {
            return Arrays.stream(user.getClass().getDeclaredFields()).filter(field -> {
                Object o1 =null;
                try {
                     o1 = field.get(user);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return ! (o1 instanceof Long);
            }).map(field -> {

                field.setAccessible(true);
                String o = null;
                try {
                    o = (String) field.get(user);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return o;
            }).collect(Collectors.toList());
        }).collect(Collectors.toList());

        System.out.println("list = " + list);

        HashMap<String, List<Question1_User>> map = new HashMap<String, List<Question1_User>>();

        users.stream().forEach(user->{

            if (list.contains(user.getEmail())) {
            }


        });

    }


    private List<Question1_User> getUser() {
        ArrayList<Question1_User> list = new ArrayList<>();
        Question1_User user1 = new Question1_User(124L, "122.126.com", "134555555555", "");
        Question1_User user2 = new Question1_User(121L, "121.126.com", "13333333333", "");
        Question1_User user3 = new Question1_User(122L, "122.126.com", "13333333333", "");
        Question1_User user4 = new Question1_User(123L, "", "13333333333", "123456");
        Question1_User user5 = new Question1_User(125L, "125.126.com", "", "123456");
        Question1_User user6 = new Question1_User(126L, "126.126.com", "", "66666");
        Question1_User user7 = new Question1_User(127L, "127.126.com", "", "777777");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);
        list.add(user7);
        return list;
    }


}
