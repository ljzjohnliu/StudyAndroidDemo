package com.study.studyjava;

import java.util.Arrays;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * 示例 3：
 *
 * 输入：nums = [1,0,1,2]
 * 输出：3
 */
public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;
        int result = 1;
        int finalResult = result;
        Arrays.sort(nums);
        //[1,2,3,4,100,200]
        //[0,1,1,2]
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i+1] - nums[i] <= 1) {
                if (nums[i+1] - nums[i] == 1) {
                    result ++;
                }
                finalResult = Math.max(finalResult, result);
            } else {
                result = 1;
            }
        }
        return finalResult;
    }

//    public int[] sortArray(int[] nums) {
//        int[] sortedArr = Arrays.sort(nums);
//        int result = 0;
//        return result;
//    }
}
