package com.fwp.study.utils;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("apple", 10);
        map1.put("orange", 20);
        map1.put("banana", 5);

        LinkedHashMap<String, Integer> sortMap1 = map1.entrySet()
                .stream()
                .sorted(Entry.comparingByValue())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        Map<String, List<Integer>> map2 = new HashMap<>();
        map2.put("apple", Lists.newArrayList(1, 1));
        map2.put("orange", Lists.newArrayList(3, 3));
        map2.put("banana", Lists.newArrayList(2, 2));

        LinkedHashMap<String, List<Integer>> sortMap2 = map2.entrySet()
                .stream()
//                .sorted(Comparator.comparing(e -> e.getValue().stream().mapToInt(Integer::intValue).sum()))
                .sorted((o1, o2) -> o2.getValue().stream().mapToInt(Integer::intValue).sum() - o1.getValue().stream().mapToInt(Integer::intValue).sum())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println("sortMap = " + sortMap1);
        System.out.println("sortMap = " + sortMap2);

    }


}
