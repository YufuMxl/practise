package com.mxl2020.algorithms.practise.stackandqueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 基本计算器 II
 *
 * @see <a href="https://leetcode-cn.com/problems/basic-calculator-ii/">LeetCode 227</a>
 */
public class BasicCalculatorII {

    /**
     * 将字符串转换成后缀表达式
     *
     * @param s 包含空格、非负数字、运算符的合法表达式，比如 " 3+5 / 2 "
     * @return 返回表达式的计算结果
     */
    public int calculate(String s) {
        // 保存逆波兰表达式
        List<String> rpnArray = new ArrayList<>();
        // 保存运算符
        Deque<String> operatorStack = new ArrayDeque<>();
        // 保存字符串中的连续数字
        StringBuilder num = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char token = s.charAt(i);
            if (Character.isDigit(token)) {
                // 遇到数字进行拼接
                num.append(token);
            } else if (' ' != token) {
                // 遇到运算符，将数字放入 rpnArray
                rpnArray.add(num.toString());
                num.delete(0, num.length());

                // 比较当前运算符和栈顶运算符的优先级
                String currentOperator = String.valueOf(token);
                while (!operatorStack.isEmpty() && getRank(operatorStack.peek()) >= getRank(currentOperator)) {
                    rpnArray.add(operatorStack.pop());
                }

                // 将当前运算符放入栈中
                operatorStack.push(currentOperator);
            }
        }

        // 将最后一个数字放入 rpnArray
        rpnArray.add(num.toString());
        // 将栈内剩余运算符放入 rpnArray
        while (!operatorStack.isEmpty()) {
            rpnArray.add(operatorStack.pop());
        }

        // 通过逆波兰表达式求值函数计算
        return new EvaluateReversePolishNotation().evalRPN(rpnArray.toArray(new String[0]));

    }

    // 获取运算符的优先级
    private int getRank(String operator) {
        if ("*".equals(operator) || "/".equals(operator)) {
            return 2;
        } else {
            return 1;
        }
    }

}
