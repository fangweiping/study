package arithmetic.leetcode;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 * 找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 */
public class Demo34_Resolve {
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,8,8,8,10};
        int count = 0;
        int target = 8;
        int[] targetArr=new int[2];
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==8){
                if (count == 0) {
                    targetArr[0]=i;
                    count++;
                }
                if (i != nums.length - 1&&nums[i]!=nums[i+1]) {
                    targetArr[1]=i;
                }
            }
        }


        int[] ints = searchRange(new int[] {5,7,7,8,8,10}, 8);
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(targetArr));
    }

    public static int[] searchRange(int[] nums, int target) {
        if(nums.length==0){
            return new int[]{-1,-1};
        }
        int count = 0;
        int[] targetArr=new int[2];
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==target){
                if (count == 0) {
                    targetArr[0]=i;
                }else {
                    targetArr[1]=i;
                }
                count++;
            }
            if (i == nums.length - 1) {
                if (nums[i] == target) {
                    if(count==0){
                        targetArr[0]=i;
                        targetArr[1]=i;
                    } else  {
                        targetArr[1]=i;
                    }
                }else {
                    if (count == 0) {
                        targetArr[0]=-1;
                        targetArr[1]=-1;
                    }else if(count==1){
                        targetArr[1]=targetArr[0];
                    }
                }
            }
        }
        return targetArr;
    }
}
