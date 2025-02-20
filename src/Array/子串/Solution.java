package Array.子串;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
     * 子数组是数组中元素的连续非空序列。
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int sum=0;
        int result=0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                result += map.get(sum - k);
            }
            map.compute(sum, (key, old) -> old == null ? 1 : old + 1);
        }
        return result;
    }

    /**
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回 滑动窗口中的最大值 。
     * 单调队列
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue monotonicQueue = new MonotonicQueue();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if(i >= k) {
                monotonicQueue.pop(nums[i-k]);
            }
            monotonicQueue.push(nums[i]);
            if(i >= k-1) {
                res[i-k+1] = monotonicQueue.getMax();
            }
        }
        return res;
    }

    /**
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * @param s
     * @param t
     * @return
     */
//    public String minWindow(String s, String t) {
//
//    }
}
