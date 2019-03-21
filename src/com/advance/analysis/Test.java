/**  
* TODO(用一句话描述该文件做什么)
* @company Finedo.cn
* @author zhangkuo
* @date 2019年2月13日 上午11:22:11
* @version V1.0  
*/
package com.advance.analysis;

import org.apache.log4j.Logger;

import java.util.Stack;

/**
 * @author dell
 *
 */

/**
 * 计算器
 */
public class Test {
    private static Logger logger = Logger.getLogger(Test.class);
    /** 数字栈：用于存储表达式中的各个数字 */
    private Stack<Long> numberStack = null;
    /** 符号栈：用于存储运算符和括号 */
    private Stack<Character> symbolStack = null;

    /**
     * 解析并计算四则运算表达式(含括号)，返回计算结果
     * ✔
     * @param numStr
     *            算术表达式(含括号)
     */
    public long caculate(String numStr) {
        numStr = removeStrSpace(numStr); // 去除空格
        // 如果算术表达式尾部没有‘=’号，则在尾部添加‘=’，表示结束符
        if (numStr.length() > 1 && !"=".equals(numStr.charAt(numStr.length() - 1) + "")) {
            numStr += "=";
        }
        // 检查表达式是否合法
        if (!isStandard(numStr)) {
            System.err.println("错误：算术表达式有误！");
            return 0;
        }
        // 初始化栈
        numberStack = new Stack<Long>();
        symbolStack = new Stack<Character>();
        // 用于缓存数字，因为数字可能是多位的
        StringBuffer temp = new StringBuffer();
        // 从表达式的第一个字符开始处理
        for (int i = 0; i < numStr.length(); i++) {
            char ch = numStr.charAt(i); // 获取一个字符
            if (isNumber(ch)) { // 若当前字符是数字
                temp.append(ch); // 加入到数字缓存中
            } else { // 非数字的情况
                String tempStr = temp.toString(); // 将数字缓存转为字符串
                if (!tempStr.isEmpty()) {
                    long num = Long.parseLong(tempStr); // 将数字字符串转为长整型数
                    numberStack.push(num); // 将数字压栈
                    temp = new StringBuffer(); // 重置数字缓存
                }
                // 判断运算符的优先级，若当前优先级低于栈顶的优先级，则先把计算前面计算出来
                while (!comparePri(ch) && !symbolStack.empty()) {
                    long b = numberStack.pop(); // 出栈，取出数字，后进先出
                    long a = numberStack.pop();
                    // 取出运算符进行相应运算，并把结果压栈进行下一次运算
                    switch ((char) symbolStack.pop()) {
                    case '+':
                        numberStack.push(a + b);
                        break;
                    case '-':
                        numberStack.push(a - b);
                        break;
                    case '*':
                        numberStack.push(a * b);
                        break;
                    case '/':
                        numberStack.push(a / b);
                        break;
                    default:
                        break;
                    }
                } // while循环结束
                if (ch != '=') {
                    // 优先级高的符号入栈
                    symbolStack.push(new Character(ch));
                    if (ch == ')') {
                        // 去掉左右括号
                        symbolStack.pop();
                        symbolStack.pop();
                    }
                }
            }
        } // for循环结束

        return numberStack.pop(); // 返回计算结果
    }

    /**
     * 去除字符串中的所有空格
     */
    private String removeStrSpace(String str) {
        return str != null ? str.replaceAll(" ", "") : "";
    }

    /**
     * 检查算术表达式的基本合法性，符合返回true，否则false
     * ✔
     */
    private boolean isStandard(String numStr) {
        if (numStr == null || numStr.isEmpty()) // 表达式不能为空
            return false;
        //这里不使用栈也可以
        Stack<Character> stack = new Stack<Character>(); // 用来保存括号，检查左右括号是否匹配
        boolean b = false; // 用来标记'='符号是否存在多个
        for (int i = 0; i < numStr.length(); i++) {
            char n = numStr.charAt(i);
            // 判断字符是否合法
            //n+""将char转化成String
            if (!(isNumber(n) || "(".equals(n + "") || ")".equals(n + "")
                    || "+".equals(n + "") || "-".equals(n + "")
                    || "*".equals(n + "") || "/".equals(n + "")
                    || "=".equals(n + ""))) {
                return false;
            }
            // 将左括号压栈，用来给后面的右括号进行匹配
            if ("(".equals(n + "")) {
                stack.push(n);
            }
            if (")".equals(n + "")) { // 匹配括号
                //将左括号出栈，说明有右括号匹配到左括号
                if (stack.isEmpty() || !"(".equals((char) stack.pop() + "")) // 括号是否匹配
                    return false;
            }
            // 检查是否有多个'='号
            // 允许末尾出现一个'='号
            if ("=".equals(n + "")) {
                if (b)
                    return false;
                b = true;
            }
        }
        // 可能会有缺少右括号的情况
        if (!stack.isEmpty())
            return false;
        // 检查'='号是否不在末尾
        if (!("=".equals(numStr.charAt(numStr.length() - 1) + "")))
            return false;
        return true;
    }

    /**
     * 判断字符是否是0-9的数字
     * ✔
     */
    private boolean isNumber(char num) {
        if (num >= '0' && num <= '9')
            return true;
        return false;
    }

    /**
     * 比较符号优先级：如果当前运算符比栈顶元素运算符优先级高则返回true，否则返回false
     * ✔
     */
    private boolean comparePri(char symbol) {
        if (symbolStack.empty()) { // 空栈返回true
            return true;
        }

        // 符号优先级说明（从高到低）:
        // 第1级: (
        // 第2级: * /
        // 第3级: + -
        // 第4级: )

        char top = (char) symbolStack.peek(); // 查看堆栈顶部的对象，注意不是出栈
        if (top == '(') {
            return true;
        }
        // 比较优先级
        switch (symbol) { 
        case '(': // 优先级最高
            return true;
        case '*': {
            if (top == '+' || top == '-') // 优先级比+和-高
                return true;
            else
                return false;
        }
        case '/': {
            if (top == '+' || top == '-') // 优先级比+和-高
                return true;
            else
                return false;
        }
        case '+':
            return false;
        case '-':
            return false;
        case ')': // 优先级最低
            return false;
        case '=': // 结束符
            return false;
        default:
            break;
        }
        return true;
    }

    // 测试
    public static void main(String args[]) {
        //10 * (2+16-20/5*4+10) + 7*2
        String num = "3*5+2*10 "; // 默认的算式
        Test test = new Test();
        Long result = test.caculate(num);
        logger.debug(result);
       
    }
}
