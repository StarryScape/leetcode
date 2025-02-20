package Array;

import java.util.*;
import java.util.stream.Collectors;

public class Array {

    //在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
    //2 <= n <= 100000
    //1.用set判重
    //2.数组下标和值存在一对多关系 遍历将其移动到对应下标 如果和下标处相等 则重复
    public int findRepeatNumber(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) {
                i++;
                continue;
            }
            if(nums[nums[i]] == nums[i]) return nums[i];
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }

    /**
     * 直接计算相邻的白格即可
     *
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        int result = 0;
//        int x,y = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                if (grid[i][j] == 1){
//                    x=i;
//                    y=j;
//                    break;
//                }else{
//                    if()
//                }
//            }
//        }
        return result;
    }

    /**
     * 是否山峰
     *
     * @param A
     * @return
     */
    public boolean validMountainArray(int[] A) {
        if(A==null||A.length<3)return false;
        int i=0;
        int j=A.length-1;
        while (i<A.length-1&&j>0&&(A[i]<A[i+1]||A[j]<A[j-1])){
            if(A[i]<A[i+1])i++;
            if(A[j]<A[j-1])j--;
        }
        return i==j&&i!=0&&i!=A.length-1;
    }

    /**
     * <a href="https://leetcode.cn/problems/container-with-most-water/?envType=study-plan-v2&envId=top-100-liked">11. 盛最多水的容器</a>
     * 双指针 每次移动低的向内 这样才有可能增长面积
     * 贪心
     * @param height
     * @return
     */
    public int maxArea(int [] height) {
        int i = 0;
        int j = height.length - 1;
        int max = 0;
        while(i < j) {
            int area = Math.min(height[i], height[j]) * (j - i);
            if(area > max) {
                max = area;
            }
            if(height[i] <= height[j]) {
                i++;
            }else {
                j++;
            }
        }
        return max;
    }

    public int trap(int[] height) {
        int result = 0;
        int c = 1;
        while(true){
            int left = -1;
            for (int i = 0; i < height.length; i++) {
                if(height[i]>c){
                    if(left == -1){
                        left = i;
                    }else{
                        result += (i-left);
                        left = -1;
                    }
                }
            }
        }
    }

    /**
     * 插入区间
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        boolean isin = false;
        for (int i = 0; i < intervals.length; i++) {
            if(intervals[i][1]<newInterval[0]){
                list.add(intervals[i]);
            }else if(intervals[i][0]>newInterval[1]){
                isin = true;
                list.add(newInterval);
                list.add(intervals[i]);
                for (int j = i; j < intervals.length; j++) {
                    list.add(intervals[j]);
                }
                break;
            }else{
                int left = Math.min(intervals[i][0],newInterval[0]);
                int right = Math.max(intervals[i][1],newInterval[1]);
                newInterval[0] = left;
                newInterval[1] = right;
            }
        }
        if(!isin)list.add(newInterval);
        return list.toArray(new int[0][]);
    }

//    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//        if(!wordList.contains(endWord))return 0;
//        if(like(beginWord,endWord))return 2;
//        if(wordList.size()==0)return 0;
//        List<String> copy = new ArrayList<>();
//        copy.addAll(wordList);
//        int result = 0;
//        for (int i = 0; i < wordList.size(); i++) {
//            String temp = wordList.get(i);
//            if(like(beginWord, temp)){
//                copy.remove(temp);
//                int result1 = ladderLength(temp,endWord,copy)+1;
//                if(result1>1&&(result == 0 || result1 < result)){
//                    result = result1;
//                }
//            }
//        }
//        return result;
//    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int result = 1;
        Set<String> set = new HashSet<>();
        set.addAll(wordList);
        Queue<String> stack = new ArrayDeque<>();
        stack.offer(beginWord);
        set.remove(beginWord);
        while(!stack.isEmpty()){
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                String temp = stack.poll();
                if(temp == endWord)return result;
                Iterator<String> iter = set.iterator();
                while(iter.hasNext()){
                    String next = iter.next();
                    if(like(temp, next)){
                        stack.offer(next);
                        iter.remove();
                    }
                }
            }
            result++;
        }
        return 0;
    }

    boolean like(String begin, String end){
        int r = 1;
        for (int i = 0; i < begin.length()&&r>-1; i++) {
            if(begin.charAt(i)!=end.charAt(i))r--;
        }
        return r==0;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        int begin = 0;
        int end = nums.length-1;

        while(begin <= end){
            int mid = (begin + end)/2;
            if(nums[mid] == target && (mid == 0 || nums[mid]>nums[mid-1])){
                result[0] = mid;
                break;
            }else if(nums[mid] <target){
                begin = mid + 1;
            }else {
                end = mid - 1;
            }
        }

        begin = 0;
        end = nums.length-1;

        while(begin < end){
            int mid = (begin + end)/2;
            if(nums[mid] == target && (mid == nums.length - 1 || nums[mid]<nums[mid+1])){
                result[1] = mid;
                break;
            }else if(nums[mid] >target){
                end = mid - 1;
            }else {
                begin = mid + 1;
            }
        }
        return result;
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static int[] reorderOddEven(int[] arr) {
        int l = 0;
        int r = arr.length - 1;

        while(l < r) {
            while(l < r && (arr[l] & 1) == 1) {
                l++;
            }
            while(r > l && (arr[r] & 1) == 0) {
                r--;
            }
            if((arr[l] & 1) == 0 && (arr[r] & 1) == 1) {
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
        }

        return arr;
    }

    /**
     * 27.移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int i = 0;
        int j = 0;
        while(j < nums.length){
            if(nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
            j++;
        }
        return i;
    }


    /**
     * <a href="https://leetcode.cn/problems/next-permutation/?envType=study-plan-v2&envId=top-100-liked">31. 下一个排列</a>
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return;
        int begin = 0;
        for (int i = nums.length - 1; i > 0 ; i--) {
            if(nums[i] > nums[i-1]) {
                //升序则需要交换 但需要在降序序列中 找到第一个比nums[i-1]大的数
                int j = i;
                while(j + 1 < nums.length && nums[j + 1] > nums[i-1]) {
                    j++;
                }
                int temp = nums[j];
                nums[j] = nums[i-1];
                nums[i-1] = temp;
                //交换后需要将i-1后面的数升序排列
                begin = i;
                break;
            }
        }
        reverse(nums, begin, nums.length - 1);
    }

    private void reverse(int[] nums, int begin, int end) {
        while(begin < end) {
            int temp = nums[begin];
            nums[begin] = nums[end];
            nums[end] = temp;
            begin++;
            end--;
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/3sum/?envType=study-plan-v2&envId=top-100-liked">三数之和</a>
     * 从给定数组中找出 三个数相加等于0的组合  且三数组合之间不能重复
     * 双指针 数组降维 三个指针 固定住一个 降维
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length - 2) {
            if(nums[i] > 0) {
                return result;
            }
            if(i == 0 || nums[i] != nums[i-1]) {
                int j = i + 1;
                int k = nums.length - 1;
                while(j < k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if(sum == 0) {
                        //只有符合 和等于0条件 才需要过滤相同数字
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        while(j < k && nums[j] == nums[j+1]) {
                            j++;
                        }
                        while(j < k && nums[k-1] == nums[k]) {
                            k--;
                        }
                        j++;
                        k--;
                    }else if(sum < 0) {
                        j++;
                    }else {
                        k--;
                    }
                }
            }
            i++;
        }
        return result;
    }

    public int splitNum(int num) {
        //最高位取最小 依次向下
        List<Integer> numList = Arrays.stream(String.valueOf(num).split("")).map(Integer::valueOf).sorted().collect(Collectors.toList());
        int numIndex = 0;
        int result = 0;
        int resultLen = numList.size() / 2;
        if(numList.size() % 2 != 0) {
            result += numList.get(numIndex++) * Math.pow(10, resultLen);
        }
        while(resultLen > 0) {
            result += numList.get(numIndex++) * Math.pow(10, resultLen - 1);
            result += numList.get(numIndex++) * Math.pow(10, resultLen - 1);
            resultLen--;
        }

        return result;
    }
    static final int MOD = 1000000007;

    public int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;
        if(n <= 1) {
            return 0;
        }
        long[] pos = new long[n];

        //移动
        for (int i = 0; i < n; i++) {
            if(s.charAt(i) == 'R') {
                pos[i] = nums[i] + d;
            }else {
                pos[i] = nums[i] - d;
            }
        }

        Arrays.sort(pos);
        long res = 0;
        for (int i = 1; i < n; i++) {
            res += (pos[i] - pos[i - 1]) * i % MOD * (n - i) % MOD;
            res %= MOD;
        }
        return (int) res;
    }

    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> result = new ArrayList<>();
        int temp = 0;
        num[num.length - 1] += k;
        for (int i = num.length - 1; i >= 0; i--) {
            num[i] += temp;
            temp = num[i] / 10;
            result.add(num[i] % 10);
        }
        while(temp != 0) {
            result.add(temp % 10);
            temp /= 10;
        }
        Collections.reverse(result);
        return result;
    }

    public List<List<Integer>> pairSums(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int num : nums) {
            map.compute(num, (key, old) -> old == null ? 1 : old + 1);
        }
        for (int num : nums) {
            if(!map.containsKey(num)) {
                continue;
            }
            map.compute(num, (key, old) -> old == 1 ? null : old - 1);
            if(map.containsKey(target - num)) {
                List<Integer> list = new ArrayList<>();
                list.add(num);
                list.add(target - num);
                result.add(list);
                map.compute(target - num, (key, old) -> old == 1 ? null : old - 1);
            }
        }
        return result;
    }

    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int[] preMaxs = new int[nums.length];
        int res = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if(i==0 || preMaxs[i-1]<=0){
                preMaxs[i] = nums[i];
            }else {
                preMaxs[i] = nums[i] + preMaxs[i-1];
            }
            if(preMaxs[i] > res) {
                res = preMaxs[i];
            }
        }
        return res;
    }

    public int[][] merge(int[][] intervals) {
        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < intervals.length; i++) {
//
//
//            map.put()
//        }

        List<Integer[]> res = new ArrayList<>();
        for (int[] interval : intervals) {
            if(res.isEmpty()) {
                res.add(Arrays.stream(interval).boxed().toArray(Integer[]::new));
            }else {
                int before = res.get(res.size()-1)[1];
                if(before < interval[0]) {
                    res.add(Arrays.stream(interval).boxed().toArray(Integer[]::new));
                }else if(res.get(res.size()-1)[1] < interval[1]){
                    res.get(res.size()-1)[1] = interval[1];
                }
            }
        }
        return res.stream()
                .map(row -> Arrays.stream(row)
                        .mapToInt(Integer::intValue)
                        .toArray())
                .toArray(int[][]::new);
    }

    /**
     * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
     * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
     * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * 你可以在 O(1) 的额外空间复杂度内完成这个题目吗？
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] answer = new int[len];
        //构造做前缀积
        answer[0] = 1;
        for (int i = 1; i < len; i++) {
            if(i==1) {
                answer[i] = nums[i-1];
            }else {
                answer[i] = answer[i-1] * nums[i-1];
            }
        }
        //一边构造后缀积 一边构造answer
        int suffix = nums[len-1];
        for (int i = len-2; i >= 0; i--) {
            answer[i] = answer[i] * suffix;
            suffix = suffix * nums[i];
        }

        return answer;
    }

    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int slow = 0;
        int fast = 1;
        while(fast < len) {
            if(nums[slow] != nums[fast]) {
                nums[++slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

    public static void main(String[] args) {
        System.out.println(new Array().pairSums(new int[]{5,6,5},  11));
    }



















}
