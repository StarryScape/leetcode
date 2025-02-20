package 回溯算法;


import java.util.*;

public class Solution {

//    给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
//    单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
//
//    例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
//
//    输入：board = [["A","B","C","E"],
//                  ["S","F","C","S"],
//                  ["A","D","E","E"]],
//         word = "ABCCED"
//    输出：true
    public boolean exist(char[][] board, String word) {
        int[][] temp = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(word.charAt(0) == board[i][j]) {
                    temp[i][j] = 1;
                    if(existRecursion(board, word, 0, i, j, temp)) return true;
                    temp[i][j] = 0;
                }
            }
        }
        return false;
    }

    //  1
    //4 from 2
    //  3
    //[["A","B","C","E"],
    // ["S","F","C","S"],
    // ["A","D","E","E"]]
    //"SEE"
    public boolean existRecursion(char[][] board, String word, int index, int x, int y, int[][] temp) {
        if(index++ == word.length() - 1) return true;

        //上
        if(x - 1 >= 0 && temp[x-1][y] != 1 && board[x-1][y] == word.charAt(index)) {
            temp[x-1][y] = 1;
            if(existRecursion(board, word, index, x-1, y, temp)) {
                return true;
            }
            temp[x-1][y] = 0;
        }
        //右
        if(y + 1 < board[0].length && temp[x][y+1] != 1 && board[x][y+1] == word.charAt(index)) {
            temp[x][y+1] = 1;
            if(existRecursion(board, word, index, x, y+1, temp)) {
                return true;
            }
            temp[x][y+1] = 0;
        }
        //下
        if(x + 1 < board.length && temp[x+1][y] != 1 && board[x+1][y] == word.charAt(index)) {
            temp[x+1][y] = 1;
            if(existRecursion(board, word, index, x+1, y, temp)) {
                return true;
            }
            temp[x+1][y] = 0;
        }
        //左
        if(y - 1 >= 0 && temp[x][y-1] != 1 && board[x][y-1] == word.charAt(index)) {
            temp[x][y-1] = 1;
            if(existRecursion(board, word, index, x, y - 1, temp)) {
                return true;
            }
            temp[x][y-1] = 0;
        }
        return false;
    }

    //给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permute(nums, new LinkedHashSet<>(), result);
        return result;
    }

    public void permute(int[] nums, Set<Integer> notOptional, List<List<Integer>> result) {
        if(nums.length == notOptional.size()) {
            result.add(new ArrayList<>(notOptional));
            return;
        }
        for (int num : nums) {
            if(!notOptional.contains(num)) {
                notOptional.add(num);
                permute(nums, notOptional, result);
                notOptional.remove(num);
            }
        }
    }
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        for (int i = 0; i <= nums.length; i++) {
            subsets(nums, 0, i, new ArrayList<>());
        }
        return result;
    }

    public void subsets(int[] nums, int begin, int len, List<Integer> single) {
        if(len == single.size()) {
            result.add(new ArrayList<>(single));
        }else {
            for (int i = begin; i < nums.length - len + single.size() + 1; i++) {
                single.add(nums[i]);
                subsets(nums, i+1, len, single);
                single.remove(single.size() - 1);
            }
        }
    }

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0)return res;
        letterCombinations(digits, 0, "");
        return res;
    }
    List<String> res = new ArrayList<>();
    public void letterCombinations(String digits, int index, String single) {
        if(index == digits.length()) {
            res.add(single);
        }else {
            for (char c : getChars(Integer.parseInt(String.valueOf(digits.charAt(index))))) {
                single += c;
                letterCombinations(digits, index+1, single);
                single = single.substring(0, single.length() - 1);
            }
        }
    }
    private char[] getChars(int i){
        if(i < 7) {
            char[] res = new char[3];
            for (int j = 0; j < 3; j++) {
                res[j] = (char) ('a' + (i - 2) * 3 + j);
            }
            return res;
        }else if(i == 7) {
            return new char[]{'p','q','r','s'};
        }else if(i == 8) {
            return new char[]{'t','u','v'};
        }else if(i == 9) {
            return new char[]{'w','x','y','z'};
        }
        return null;
    }

    public static void main(String[] args) {
        new Solution().letterCombinations(23+"");
    }
}
