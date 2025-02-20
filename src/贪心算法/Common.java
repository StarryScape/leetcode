package 贪心算法;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 贪心算法
 * 贪心算法是指在对问题求解时，总是做出在当前看来是最好的选择。也就是说，不从整体最优上加以考虑，只做出在某种意义上的局部最优解。
 * 贪心算法不是对所有问题都能得到整体最优解，关键是贪心策略的选择，选择的贪心策略必须具备无后效性，即某个状态以前的过程不会影响以后的状态，只与当前状态有关。
 *
 * 解题的一般步骤是：
 * 1.建立数学模型来描述问题；
 * 2.把求解的问题分成若干个子问题；
 * 3.对每一子问题求解，得到子问题的局部最优解；
 * 4.把子问题的局部最优解合成原来问题的一个解。
 *
 * 设1元、2元、5元、10元、20元、50元、100元的纸币分别有c0, c1, c2, c3, c4, c5, c6张。现在要用这些钱来支付K元，至少要用多少张纸币？用贪心算法的思想，很显然，每一步尽可能用面值大的纸币即可
 * 像这个例子 局部最优解 就是每次找出最大的 最终组成整体最优解
 *
 * @Author guotiangang
 * @Date 2023/4/6 17:41
 */
public class Common {

    /**
     * 有两只老鼠和 n 块不同类型的奶酪，每块奶酪都只能被其中一只老鼠吃掉。
     *
     * 下标为 i 处的奶酪被吃掉的得分为：
     *
     * 如果第一只老鼠吃掉，则得分为 reward1[i] 。
     * 如果第二只老鼠吃掉，则得分为 reward2[i] 。
     * 给你一个正整数数组 reward1 ，一个正整数数组 reward2 ，和一个非负整数 k 。
     *
     * 请你返回第一只老鼠恰好吃掉 k 块奶酪的情况下，最大 得分为多少。
     */
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int score = 0;
        //查找老鼠1 差值最大的k个奶酪
        //老鼠2全吃了  求差值最大的k个 即为最大得分 逆向思维
        int[] diffs = new int[reward1.length];
        for (int i = 0; i < reward1.length; i++) {
            diffs[i] = (reward1[i] - reward2[i]);
        }
        Arrays.sort(diffs);
        for (int val2 : reward2) {
            score += val2;
        }
        for (int i = 0; i < k; i++) {
            score += diffs[diffs.length - 1 - i];
        }
        return score;
    }

    /**
     * 给你一个整数数组 nums，请你找出并返回最大和且能被三整除。
     * @param nums
     * @return
     */
    public int maxSumDivThree(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int sum = 0;
        //本身能被三整除
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[i] = nums[i] % 3;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if(temp[i] == 0){
                sum += nums[i];
            }else if(temp[i] == -1) {
                continue;
            }else {
                for (int j = i - 1; j >= 0; j--) {
                    if(temp[j] + temp[i] == 3){
                        sum += (nums[i] + nums[j]);
                        temp[j] = -1;
                        break;
                    }
                }
            }
        }
        return sum;
    }

    /**
     * https://leetcode.cn/problems/apple-redistribution-into-boxes/
     * 苹果和箱子
     */
    public int minimumBoxes(int[] apple, int[] capacity) {
        int appleCount = 0;
        for (int i : apple) {
            appleCount += i;
        }
        Arrays.sort(capacity);
        int result = 0;
        for (int i = capacity.length - 1; i >= 0; i--) {
            result++;
            if(appleCount <= capacity[i]) {
                return result;
            }else {
                appleCount -= capacity[i];
            }
        }
        return -1;
    }

    /**
     * https://leetcode.cn/problems/jump-game/?envType=study-plan-v2&envId=top-100-liked
     * 非负整数数组 数字代表当前位置最大步数 判断是否能跳到最后一格
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if(nums.length == 1) {
            return true;
        }
        int i = nums.length - 1;
        return canJump(nums, nums.length - 1);
    }

    public boolean canJump(int[] nums, int index) {
        for (int j = 0; j < index; j++) {
            if(nums[j] >= index -j) {
                if(canJump(nums, index - 1)) {
                    return true;
                }
            }
        }
        return index == 0;
    }

    public static void main(String[] args) {
        System.out.println(new Common().canJump(new int[]{2,0,6,9,8,4,5,0,8,9,1,2,9,6,8,8,0,6,3,1,2,2,1,2,6,5,3,1,2,2,6,4,2,4,3,0,0,0,3,8,2,4,0,1,2,0,1,4,6,5,8,0,7,9,3,
                4,6,6,5,8,9,3,4,3,7,0,4,9,0,9,8,4,3,0,7,7,1,9,1,9,4,9,0,1,9,5,7,7,1,5,8,2,8,2,6,8,2,2,7,5,1,7,9,6}));
    }
}
