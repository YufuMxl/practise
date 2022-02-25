package com.mxl2020.algorithms.practise.stackandqueue;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 有效的括号
 *
 * @see <a href="https://leetcode-cn.com/problems/valid-parentheses/">LeetCode 20</a>
 */
public class ValidParentheses {

    private final Map<Character, Character> parenMap = new HashMap<>();

    public ValidParentheses() {
        parenMap.put('(', ')');
        parenMap.put('[', ']');
        parenMap.put('{', '}');
    }

    /**
     * 1.遍历到左括号，入栈
     * <p>
     * 2.遍历到右括号，与栈顶元素进行匹配：如果匹配上，栈顶元素出栈；如果不匹配，return false
     * <p>
     * 3.字符串遍历结束，栈必须是空的，如果非空，return false
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public boolean isValid(String s) {
        if (s == null) return false;

        Deque<Character> parenStack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            Character parenChar = s.charAt(i);
            if (parenMap.containsKey(parenChar)) {
                // 遇到左括号，入栈
                parenStack.push(parenChar);
            } else {
                // 遇到右括号，与栈顶元素匹配
                if (parenStack.isEmpty() || parenMap.get(parenStack.pop()) != parenChar) {
                    return false;
                }
            }
        }
        return parenStack.isEmpty();
    }

}
