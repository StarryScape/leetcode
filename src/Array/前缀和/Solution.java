package Array.前缀和;

public class Solution {

    /**
     * <a href="https://leetcode.cn/problems/running-sum-of-1d-array/description/">1480. 一维数组的动态和</a>
     * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
     * 请返回 nums 的动态和。
     * @param nums
     * @return
     */
    public int[] runningSum(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        if(nums.length == 0) {
            return res;
        }

        for (int i = 0; i < n; i++) {
            if(i == 0) {
                res[i] = nums[i];
            }else {
                res[i] = nums[i - 1] + nums[i];
            }
        }
        return res;
    }

    class NumArray {

        int[] prenums = null;

        public NumArray(int[] nums) {
            prenums = new int[nums.length+1];
            for (int i = 0; i < nums.length; i++) {
                prenums[i+1] = prenums[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return prenums[right+1] - prenums[left];
        }
    }

    static class NumMatrix {
        int[][] prematrix = null;

        /**
         * [3,0,1,4,2],
         * [5,6,3,2,1],
         * [1,2,0,1,5],
         * [4,1,0,1,7],
         * [1,0,3,0,5]
         * @param matrix
         */
        public NumMatrix(int[][] matrix) {
            prematrix = new int[matrix.length+1][matrix[0].length+1];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    prematrix[i+1][j+1] = matrix[i][j] + prematrix[i][j+1] + prematrix[i+1][j] - prematrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return prematrix[row2+1][col2+1] - prematrix[row1][col2+1] - prematrix[row2+1][col1] + prematrix[row1][col1];
        }
    }

    public static void main(String[] args) {
    }
}
