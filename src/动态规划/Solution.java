package 动态规划;

import java.util.Arrays;

public class Solution {

    /**
     * 这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。
     * 给定三个整数 n ,  k 和 target ，返回可能的方式(从总共 kn 种方式中)滚动骰子的数量，使正面朝上的数字之和等于 target 。
     * 答案可能很大，你需要对 109 + 7 取模 。
     * 2 7
     * @param n
     * @param k
     * @param target
     * @return
     */
    public int numRollsToTarget(int n, int k, int target) {
//        dp[i][j] 代表 扔 i 个骰子和为 j；  找递推公式是真的难
        int[][] dp = new int[n+1][target+1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if(i==1 && j > k) {
                    continue;
                }
                if(i == 1){
                    dp[i][j] = 1;
                }else {
                    int m = 1;
                    while(m<j){
                        if(j-m <= k && j-m > 0&& dp[i-1][m] > 0) {
                            dp[i][j] += dp[i-1][m];
                        }
                        dp[i][j] = dp[i][j] % 1000000007;
                        m++;
                    }
                }
            }
        }
        printArray(dp);
        return dp[n][target];
    }

    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println(); // 输出换行符
        }
    }

    //给你一个下标从 1 开始、大小为 m x n 的整数矩阵 mat，你可以选择任一单元格作为 起始单元格 。
    //从起始单元格出发，你可以移动到 同一行或同一列 中的任何其他单元格，但前提是目标单元格的值 严格大于 当前单元格的值。
    //你可以多次重复这一过程，从一个单元格移动到另一个单元格，直到无法再进行任何移动。
    //请你找出从某个单元开始访问矩阵所能访问的 单元格的最大数量 。
    //返回一个表示可访问单元格最大数量的整数。
    //输入：mat = [[3,1],
    //            [3,4]]
    //输出：2
    //解释：上图展示了从第 1 行、第 2 列的单元格开始，可以访问 2 个单元格。可以证明，无论从哪个单元格开始，最多只能访问 2 个单元格，因此答案是 2 。
//    public int maxIncreasingCells(int[][] mat) {
//
//    }

    public static void main(String[] args) {
        new Solution().numRollsToTarget(30,30, 500);
    }
}
