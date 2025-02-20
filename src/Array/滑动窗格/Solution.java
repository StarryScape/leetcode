package Array.滑动窗格;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 滑动窗格记录
 *
 * @Author guotiangang
 * @Date 2022/8/26 11:40
 */
public class Solution {

    /**
     * 给定一个 排序好 的数组arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
     *
     * 整数 a 比整数 b 更接近 x 需要满足：
     *
     * |a - x| < |b - x| 或者
     * |a - x| == |b - x| 且 a < b
     **/
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        int left = 0;
        int right = k - 1;
        while(right < arr.length - 1 && (Math.abs(arr[left] - x) > Math.abs(arr[right + 1] - x) || arr[left] == arr[right + 1])) {
            left ++;
            right ++;
        }
        while(left <= right) {
            list.add(arr[left++]);
        }
        return list;
    }

    /**
     * <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/?envType=study-plan-v2&envId=top-100-liked">无重复字符的最长子串</a>
     * 滑动窗格 一旦有重复收缩起始位置
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0) {
            return 0;
        }
        int result = 0;
        Map<Character, Integer> indexMap = new HashMap<>();
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            Character after = s.charAt(j);
            if(indexMap.containsKey(after)) {
                i = Math.max(indexMap.get(after) + 1, i);
            }
            indexMap.put(after, j);
            result = Math.max(result, j - i + 1);
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/?envType=study-plan-v2&envId=top-100-liked">找到字符串中所有字母异位词</a>
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        //固定大小窗格向右滑动
        return null;
    }







}
