package hash;

import java.util.*;

public class Intersect {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            list1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if(list1.contains(nums2[i])){
                list1.remove((Integer)nums2[i]);
                list.add(nums2[i]);
            }
        }
        return list.stream().mapToInt(i -> (int)i).toArray();
    }

    /**
     * <a href="https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-100-liked">最长连续序列</a>
     * 遍历 并记录当前数 左右两边的连续数的长度 并更新左右端点的值
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        //O(n)
        //[100,4,200,1,3,2] --> [1, 2, 3, 4] --> 4
        //[0,3,7,2,5,8,4,6,0,1]
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if(map.containsKey(num)) {
                continue;
            }
            int len = 1;
            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            len = len + left + right;
            if(len > max) {
                max = len;
            }
            map.put(num, len);
            map.put(num - left, len);
            map.put(num + right, len);
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3}));
    }
}
