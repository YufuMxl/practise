package com.mxl2020.algorithms.practise.stackandqueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
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
        Deque<Character> parenStack = new ArrayDeque<>();
        for (Character parenChar : s.toCharArray()) {
            if (parenMap.containsKey(parenChar)) parenStack.push(parenChar);
            else if (parenStack.isEmpty() || parenMap.get(parenStack.pop()) != parenChar) return false;
        }
        return parenStack.isEmpty();
    }

}
