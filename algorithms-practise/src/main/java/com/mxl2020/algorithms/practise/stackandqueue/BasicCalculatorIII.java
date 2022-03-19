package com.mxl2020.algorithms.practise.stackandqueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 基本计算器 III
 *
 * @see <a href="https://leetcode-cn.com/problems/basic-calculator/">LeetCode 224</a>
 */
public class BasicCalculatorIII {
    /**
     * 将字符串转换成后缀表达式
     * <p>
     * 在 BasicCalculatorII 的基础上，解决括号和负数的问题
     * <ol>
     * <li>如果遇到左括号，左括号直接入栈
     * <li>如果遇到右括号，栈顶元素不断出栈并放入 rpnArray，直到左括号出栈（左括号不放入 rpnArray，右括号不放入 operatorStack）
     * <li>如果遇到运算符，只要栈顶符号的优先级 >= 该运算符，就不断取出栈顶符号放入 rpnArray，最后该运算符入栈。优先级为 乘除 > 加减 > 左括号
     * </ol>
     * <p>
     * 注意点
     * <li>谨慎处理 rpnArray 的数字入组问题
     * <li>负号的处理
     * <ol>
     * <li>当负号出现在字符串首位，如果后面跟着数字，直接将负号拼入数字；如果后面跟着左括号，在负号前添 0
     * <li>当负号出现在字符串中间，负号前面一定是一个"+ - * / ("符号
     * </ol>
     *
     * @param s 包含空格、正负整数、运算符、小括号的合法表达式，比如 "(-1+(4+5+2)-3) + (6+8)"
     * @return 返回表达式的计算结果
     */
    public int calculate(String s) {
        // TODO 负数补零优化
        if (s.startsWith("-(") || s.startsWith("- (")) {
            s = "0" + s;
        }
        s = s.replace("(- (", "(0- (");

        List<String> rpnArray = new ArrayList<>();
        Deque<String> operatorDeque = new ArrayDeque<>();
        StringBuilder num = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char token = s.charAt(i);

            if (' ' == token) continue;

            if (Character.isDigit(token)) {
                num.append(token);
                continue;
            }

            // 处理负数
            if (token == '-') {
                if (i == 0 && Character.isDigit(s.charAt(i + 1))) {
                    num.append(token);
                    continue;
                }
                if (i != 0) {
                    char preChar = s.charAt(i - 1);
                    if ('+' == preChar || '-' == preChar || '*' == preChar || '/' == preChar || '(' == preChar) {
                        num.append(token);
                        continue;
                    }
                }
            }

            if (num.length() > 0) {
                rpnArray.add(num.toString());
                num.delete(0, num.length());
            }

            // 处理括号和运算符
            if ('(' == token) {
                operatorDeque.push(String.valueOf(token));
            } else if (')' == token) {
                while (!"(".equals(operatorDeque.peek())) {
                    rpnArray.add(operatorDeque.pop());
                }
                operatorDeque.pop();
            } else {
                String currentOperator = String.valueOf(token);
                while (getRank(operatorDeque.peek()) >= getRank(currentOperator)) {
                    rpnArray.add(operatorDeque.pop());
                }
                operatorDeque.push(currentOperator);
            }
        }

        if (num.length() > 0) {
            rpnArray.add(num.toString());
        }

        while (!operatorDeque.isEmpty()) {
            rpnArray.add(operatorDeque.pop());
        }

        return new EvaluateReversePolishNotation().evalRPN(rpnArray.toArray(new String[0]));

    }

    private int getRank(String operator) {
        if ("*".equals(operator) || "/".equals(operator)) {
            return 2;
        } else if ("+".equals(operator) || "-".equals(operator)) {
            return 1;
        } else if ("(".equals(operator)) {
            return 0;
        } else {
            return -1;
        }
    }

}
