package sort;

import java.util.Arrays;

/**
 * 冒泡排序:
 *
 * 两两交换
 *
 *
 */
public class MaoPao {
    public static void main(String[] args) {
        int[] arr = {1,24,5,52,5,7,93,2};

        for (int i = 0; i < arr.length-1; i++) { //外层循环控制排序趟数
            for (int j = 1; j < arr.length-i-1; j++) {//内层循环控制每一趟排序多少次
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
