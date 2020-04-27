package jdk8.lambda;

import java.util.Optional;

public class OptionalDemo  {
    public static void main(String[] args) {

        Optional<Object> empty = Optional.empty();
        Optional<Object> nulls = Optional.of(null);
        Optional<Object> nullable = Optional.ofNullable(null);

        System.out.println("empty = " + empty);
        System.out.println("nulls = " + nulls);
        System.out.println("nullable = " + nullable);


    }
}
