package jvm;

import java.util.ArrayList;
import java.util.List;

public class OOMDemo {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        while (true) {
            list.add("OOM String");
        }

    }
}
