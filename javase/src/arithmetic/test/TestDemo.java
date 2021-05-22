package arithmetic.test;

import arithmetic.lambda.User;

import java.util.ArrayList;

public class TestDemo {

    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        User user = new User(11, 11);
        list.add(user);
        list.add(user);
        System.out.println("user = " + user);
    }
}
