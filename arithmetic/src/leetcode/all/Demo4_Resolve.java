package leetcode.all;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5

 */
public class Demo4_Resolve {
    public static void main(String[] args) {

    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double num = 0.0;
        int[] newArr = new int[nums1.length + nums2.length];

        for (int i = 0, j = 0; i < newArr.length; i++) {
            if (i <= nums1.length - 1) {
                newArr[i] = nums1[i];
            } else {
                newArr[i] = nums2[j];
                j++;
            }
        }

        for (int i = 0; i < newArr.length - 1; i++) {
            for (int j = 0; j < newArr.length - 1 - i; j++) {
                if (newArr[j] > newArr[j + 1]) {
                    int temp = newArr[j];
                    newArr[j] = newArr[j + 1];
                    newArr[j + 1] = temp;
                }
            }
        }

        if (newArr.length % 2 == 0) {
            num = (((double) newArr[newArr.length / 2] + (double) newArr[newArr.length / 2 - 1]) / 2);
        } else {
            num = newArr[newArr.length / 2];
        }
        return num;
    }
}
