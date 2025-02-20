package String;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Strings {

    public boolean isLongPressedName(String name, String typed) {
        char pre = ' ';
        int i = 0,j=0;
        while (i<name.length()&&j<typed.length()){
            if(name.charAt(i) == typed.charAt(j)){
                pre = name.charAt(i);
                j++;
                i++;
            }else if (pre == typed.charAt(j)){
                j++;
            }else{
                return false;
            }
        }
        return i==name.length()&&typed.length()==j;
    }

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     * 限制：
     * 0 <= s 的长度 <= 10000
     **/
    public String replaceSpace(String s) {
        if(s.length() == 0) return s;
        if(s.length() == 1 && s.equals(" ")) return "%20";

        int spaceNum = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ') spaceNum++;
        }

        if(spaceNum != 0) {
            char[] arr = new char[s.length() + 2 * spaceNum];
            int index = arr.length - 1;
            for (int i = s.length() - 1; i >= 0; i--) {
                if(s.charAt(i) == ' ') {
                    arr[index--] = '0';
                    arr[index--] = '2';
                    arr[index--] = '%';
                } else {
                    arr[index--] = s.charAt(i);
                }
            }

            return new String(arr);
        }

        return s;
    }

    /**
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     *
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     */
    public String convert(String s, int numRows) {
        // 0 + (numRows - 1) * 2
        // 1  (numRows - 2) * 2
        //。。。
        //last (numRows - 1) * 2
        if(numRows == 1) return s;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            if(i == 0) {
                int index = 0;
                while (index < s.length()) {
                    result.append(s.charAt(index));
                    index += (numRows - 1) * 2;
                }
            } else if(i == numRows - 1) {
                int index = numRows - 1;
                while (index < s.length()) {
                    result.append(s.charAt(index));
                    index += (numRows - 1) * 2;
                }
            } else {
                int index = i;
                while (index < s.length()) {
                    result.append(s.charAt(index));
                    index += (numRows - 1 - i) * 2;
                    if(index < s.length()) {
                        result.append(s.charAt(index));
                        index += i * 2;
                    }
                }
            }
        }
        return result.toString();

    }

    /**
     * https://leetcode.cn/problems/group-anagrams/?envType=study-plan-v2&envId=top-100-liked
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(str -> str.chars().sorted().mapToObj(Objects::toString).collect(Collectors.joining()))).values());
    }

    public int findTheLongestBalancedSubstring(String s) {
        if(s.length() < 2)return 0;
        int res = 0;
        int i = 0;
        while(i < s.length()) {
            if(s.charAt(i) == '0') {
                int j = i;
                int len = 0;
                boolean findZero = true;
                while(j < s.length()) {
                    if(s.charAt(j) == '0') {
                        if(findZero) {
                            len++;
                        }else {
                            break;
                        }
                    }else {
                        if(findZero) {
                            findZero = false;
                        }
                        if(len > 0) {
                            len --;
                        }else {
                            break;
                        }
                    }
                    j++;
                }
                int temp = j - i - len;
                if(temp > res) {
                    res = temp;
                }
                i = j;
            }else {
                i++;
            }
        }
        return res;
    }

    //给定字符串列表 strs ，返回其中 最长的特殊序列 的长度。如果最长特殊序列不存在，返回 -1 。
    //特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
    // s 的 子序列可以通过删去字符串 s 中的某些字符实现。
    //例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
    //["aabbcc", "aabbcc","cb"]
    public int findLUSlength(String[] strs) {
        int n = strs.length;
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            boolean check = true;
            for (int j = 0; j < n; ++j) {
                if (i != j && isSubseq(strs[i], strs[j])) {
                    check = false;
                    break;
                }
            }
            if (check) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        return ans;
    }

    public boolean isSubseq(String s, String t) {
        int ptS = 0, ptT = 0;
        while (ptS < s.length() && ptT < t.length()) {
            if (s.charAt(ptS) == t.charAt(ptT)) {
                ++ptS;
            }
            ++ptT;
        }
        return ptS == s.length();
    }

    public String discountPrices(String sentence, int discount) {
        StringBuilder result = new StringBuilder();
        String[] words = sentence.split(" ");
        double discountMultiplier = 1 - discount / 100.0;

        for (String word : words) {
            if (word.startsWith("$") && word.length() > 1 && isDigits(word.substring(1))) {
                double price = Double.parseDouble(word.substring(1));
                result.append(String.format("$%.2f", price * discountMultiplier)).append(" ");
            } else {
                result.append(word).append(" ");
            }
        }

        return result.toString().trim();
    }

    public boolean isDigits(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}
