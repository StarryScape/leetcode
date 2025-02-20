package Array.矩阵;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。  仅用常量空间
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        boolean a = false;
        boolean b = false;
        //哪行 哪列置0
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (ints[j] == 0) {
                    if (matrix[0][j] == 0) {
                        a = true;
                    }
                    if (ints[0] == 0) {
                        b = true;
                    }
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if(matrix[0][j] == 0 || matrix[i][0] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        if(a) {
            Arrays.fill(matrix[0], 0);
        }
        if(b) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0]=0;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    public void rotate(int[][] matrix) {
        //0,0  0,3
        //0,3  3,3
        //0,1  1,3
        //1,1  1,2
        //x,y  y,len-1-x
        //3,2  2,0
        //1,2  2,3
        int len = matrix.length;
        for (int i = 0; i < len/2; i++) {
            for (int j = i; j < len-i-1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len-1-j][i];//1 1 3 1
                matrix[len-1-j][i] = matrix[len-1-i][len-1-j];
                matrix[len-1-i][len-1-j] = matrix[j][len-1-i];
                matrix[j][len-1-i] = temp;
            }
        }
    }

    /**
     * [[1,2,3],
     *  [4,5,6],
     *  [7,8,9]]
     * [[1, 2, 3, 4]
     * ,[5, 6, 7, 8]
     * ,[9,10,11,12]
     * ,[9,10,11,12]]
     * 1   2   3   4   5
     * 6   7   8   9   10
     * 11  12  13  14  15
     * 16  17  18  19  20
     * 21  22  23  24  25
     **/
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix.length == 0) return res;
        int m = matrix.length;
        int n = matrix[0].length;
        int direct = 1;
        int i = 0;
        int j = 0;
        while(i>=0&&j>=0&&i< m && j < n && matrix[i][j] != 101) {
            res.add(matrix[i][j]);
            matrix[i][j] = 101;
            if(direct == 1) {
                if(j+1 < n && matrix[i][j+1]!=101) {
                    j++;
                }else {
                    i++;
                    direct = 2;
                }
            } else if(direct == 2) {
                if(i+1 < m && matrix[i+1][j]!=101) {
                    i++;
                }else {
                    j--;
                    direct = 3;
                }
            }else if(direct == 3) {
                if(j-1 >= 0 && matrix[i][j-1]!=101) {
                    j--;
                }else {
                    i--;
                    direct = 4;
                }
            }else {
                if(i-1 >= 0 && matrix[i-1][j]!=101) {
                    i--;
                }else {
                    j++;
                    direct = 1;
                }
            }
        }
        return res;
    }

    /**
     * [[1,4,7,11,15]
     * ,[2,5,8,12,19]
     * ,[3,6,9,16,22]
     * ,[10,13,14,17,24]
     * ,[18,21,23,26,30]]  从右上角看是二叉搜索树？
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length - 1;
        while(i<matrix.length && j>=0) {
            if(matrix[i][j] == target){
                return  true;
            }else  if(matrix[i][j] > target) {
                j--;
            }else {
                i++;
            }
        }
        return false;
    }

    boolean searchMatrix(int[][] matrix, int target, int i ,int j) {
        if(matrix[i][j] == target) return true;
        boolean result = false;
        if(i+1 < matrix.length && target >= matrix[i+1][j]) {
            result = searchMatrix(matrix, target, i+1, j);
        }
        if(!result && j+1<matrix[0].length && target >= matrix[i][j+1]) {
            result = searchMatrix(matrix, target, i, j+1);
        }
        return result;
    }

    public static void main(String[] args) {
        new Solution().searchMatrix(new int[][]{
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22}},5);
    }
}
