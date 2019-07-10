package leetcode;

import java.util.ArrayList;
import java.util.HashMap;

public class Demo {
    private  static   HashMap<String,Double> originMap = new HashMap<>();//key 行列号,  value , 该行列号对应的单元格值  ,字段v1;
    private  static   HashMap<String,Double> updateMap = new HashMap<>();//key 行列号,  value , 该行列号对应的单元格值  ,字段v1;

    public static void main(String[] args) {
        ArrayList<Integer> selectCurveVersionId = new ArrayList();//按顺序保存曲线ID

        //选中事件 封装 originmap
        int col = 0;
        int row = 0;
        double cellValue = 1.0;

        String key = col+","+row;//拼接选中的行列号
        Double value = cellValue;
        //先判断map里是否有该key
        if (!originMap.containsKey(key)) {
            originMap.put(key,value);
        }
    }
    public void show() {
        //表格修改事件,封装updatemap
        int col = 0;
        int row = 0;
        String key = col+","+row;//拼接选中的行列号
        double cellValue =1.0;

        updateMap.put(key,cellValue);
    }
}
