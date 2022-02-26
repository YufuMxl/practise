package com.mxl2020.algorithms.practise.stackandqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 逆波兰表达式/后缀表达式求值
 *
 * @see <a href="https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/">LeetCode 150</a>
 */
public class EvaluateReversePolishNotation {

    /**
     * 思路
     * <ol>
     * <li>遍历 tokens，遇到数字入栈
     * <li>遇到运算符，出栈两个数字与该运算符做运算，运算结果入栈
     * <li>遍历结束，栈里就剩最后一个元素，就是最终结果
     * </ol>
     * 时间复杂度 O(n)
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> operandStack = new ArrayDeque<>();
        for (String token : tokens) {
            if (isOperator(token)) {
                // 遇到运算符，取栈顶两个数字，进行运算
                int b = operandStack.pop();
                int a = operandStack.pop();
                // 将计算结果入栈
                operandStack.push(calculate(a, b, token));
            } else {
                // 遇到数字入栈
                operandStack.push(Integer.valueOf(token));
            }
        }
        // 栈里剩的最后一个元素就是最终结果
        return operandStack.pop();
    }

    private boolean isOperator(String token) {
        if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
            return true;
        } else {
            return false;
        }
    }

    private int calculate(int a, int b, String operator) {
        if ("+".equals(operator)) {
            return a + b;
        } else if ("-".equals(operator)) {
            return a - b;
        } else if ("*".equals(operator)) {
            return a * b;
        } else if ("/".equals(operator)) {
            return a / b;
        } else {
            return 0;
        }
    }

}
