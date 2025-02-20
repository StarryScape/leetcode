package stack;

import java.util.Stack;

public class StackProblem {

    /**
     * <a href="https://leetcode-cn.com/problems/decode-string/">字符串解码</a>
     * 输入：s = "3[a2[c]]"
     * 输出："accaccacc"
     * @param s
     * @return
     */
    public String decodeString(String s) {
        Stack<Object> stack = new Stack<>();
        String result = "";
        int num = 0;
        for (char c : s.toCharArray()) {
            if(c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            }else if(c == '[') {
                stack.push(num);
                num = 0;
            }else if(c >= 'a' && c <= 'z') {
                stack.push(c);
            }else if(c == ']') {
                String temp = stack.pop().toString();
                while(!stack.isEmpty() && (stack.peek() instanceof Character || stack.peek() instanceof String)) {
                    temp = stack.pop().toString() + temp;
                }
                if(!stack.isEmpty() && stack.peek() instanceof Integer) {
                    int count = (int)stack.pop();
                    stack.push(mul(count, temp));
                }else {
                    stack.push(temp);
                }

            }
        }
        while(!stack.isEmpty()) {
            result = stack.pop().toString() + result;
        }
        return result;
    }

    private String mul(int num, String str) {
        String result = "";
        for (int i = 0; i < num; i++) {
            result += str;
        }
        return result;
    }

    public static void main(String[] args) {
        StackProblem stackProblem = new StackProblem();
        System.out.println(stackProblem.decodeString("2[2[y]pq4[2[jk]e1[f]]]"));
    }
}
