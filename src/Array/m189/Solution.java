package Array.m189;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * 1 2 3 4 5
     * 3 4 5 1 2
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        //用最大公约数 分组  每个组依次循环交换 原地
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);
        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
        System.out.println(Arrays.toString(nums));
    }

    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    public static void main(String[] args) {
        new Solution().rotate(new int[]{-1,-100,3,99}, 2);
        System.out.println(new Solution().gcd(181,11));
    }
}