package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 它们都属于内部排序，也就是只考虑数据量较小仅需要使用内存的排序算法，他们之间关系如下：
 *  ⎧⎩⎨⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪内部排序⎧⎩⎨⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪⎪
 *  插入排序      直接插入排,序希尔排序
 *  选择排序       简单选择排序,堆排序
 *  交换排序       冒泡排序,快速排序
 *  基数排序
 *  归并排序
 *  稳定与非稳定:
 *
 * 如果一个排序算法能够保留数组中重复元素的相对位置则可以被称为是 稳定 的。反之，则是 非稳定 的。
 */
public class ArithmeticDemo {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
       list.add(1);
       list.add(13);
       list.add(4);
       list.add(5);
       list.add(66);
       list.add(22);

        List<Integer> selectSort = selectSort(list);
        System.out.println("selectSort = " + selectSort);
    }


    public static List<Integer> maopao(List<Integer> list) {
        /*
        *   两两交换
        * */

        for (int i = 0; i < list.size()-1; i++) {   //外层循环控制排序趟数
            for (int j = 0; j < list.size()-1-i; j++) { //内层循环控制每一趟排序多少次
                if (list.get(j)>list.get(j+1)) {
                    int temp = list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,temp);
                }
            }
        }
        return list;
    }

    public static List<Integer> selectSort(List<Integer> list) {
        //原理：每一趟从待排序的记录中选出最小的元素，顺序放在已排好序的序列最后，直到全部记录排序完毕

        for (int i = 0; i < list.size(); i++) {//做第i趟排序
            //最小元素的索引
            int minIndex = i ;
            for (int j = minIndex+1; j < list.size(); j++) {// 选最小的记录
                if (list.get(j)<list.get(minIndex)) {
                    minIndex=j;//记下目前找到的最小值所在的位置
                }
            }
            //在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
            int temp = list.get(i);
            list.set(i,list.get(minIndex));
            list.set(minIndex,temp);
        }
        return list;
    }
}
