package com.sgwl.filemanager.utils;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;

public class DataHandle {

    /**
     * list - > map
     * 自定义泛型方法,对象的pid作为key,具有相同pid的对象作放入list中并作为value ,存入map中
     * @param list
     * @param filedName
     * @param <T>
     * @return
     */
    @SuppressWarnings("all")
    public <T> Map<Long, List<T>> listToMap(List<T> list,String pid) {
        //创建map容器
        Map<Long, List<T>> map = new LinkedHashMap<>();
        //定义map的key保存对象的pid
        Long key = null;

        if (CollectionUtils.isEmpty(list)) {
            return map ;
        }

        //获取泛型真实对象的Class对象
        Class<?> clazz = list.get(0).getClass();

        try {
            //获取真实类所有属性
            Field[] fields = clazz.getDeclaredFields();
            for (T t : list) {
                for (Field field : fields) {
                    //因为字段是私有的，所以得暴力反射
                    field.setAccessible(true);
                    //获取字段名
                    String fieldName = field.getName();
                    //获取指定字段的值
                    if (fieldName.equals(pid)) {
                        //获取真实对象的属性值
                        Object fieldValue = field.get(t);
                        key = (Long) fieldValue;
                    }
                }

                //定义map的value保存具有相同pid的对象集合
                List<T> value = new LinkedList<>();

                //最终添加到map中,完成 list -> map
                if (!map.containsKey(key)) {//key不存在,添加新数据
                    value.add(t);
                    map.put(key, value);
                } else {//key已存在,取出value,追加新数据
                    List<T> oldValue = map.get(key);
                    oldValue.add(t);
                    value = oldValue;
                    map.put(key, value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

}
