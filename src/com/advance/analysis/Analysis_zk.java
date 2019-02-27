package com.advance.analysis;

import com.advance.Engine_and_Message.util.NonUtil;
import com.advance.JDBC.Connect_Greenplum;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.util.*;

/**
 * @Title:  Analysis_zk.java
 * @Description: 规则解析工具
 * @author: zhangkuo
 * @date:   2019-2-18 上午9:03:28
 */
public class Analysis_zk {
    private static Logger logger = LogManager.getLogger(Analysis.class);
    /**
     * 条件栈：用于存储表达式中的各个条件
     */
    private Stack<String> numberStack = null;
    /**
     * 符号栈：用于存储运算符和括号
     */
    private Stack<Character> symbolStack = null;

    /**
     *
     * @Title: caculate
     * @Description: 解析并计算规则表达式(含括号)，返回计算结果
     * @Author: chenjinyao
     * @param: @param numStr 规则表达式(含括号)
     * @param: @return
     * @param: @throws Exception
     * @return: LinkedHashMap<String,Set<String>>
     * @throws
     */
    public Set<String> caculate(String numStr) throws Exception {
        numStr = removeStrSpace(numStr); // 去除空格
        // 如果规则表达式尾部没有‘#’号，则在尾部添加‘#’，表示结束符
        if (numStr.length() > 1 && !"#".equals(numStr.charAt(numStr.length() - 1) + "")) {
            numStr += "#";
        }
        // 检查表达式是否合法
        if (!isStandard(numStr)) {
            logger.debug("错误：规则表达式有误！");
            return null;
        }
        // 初始化栈
        numberStack = new Stack<String>();
        symbolStack = new Stack<Character>();
        // 用于缓存条件，因为条件可能是多位的
        StringBuffer temp = new StringBuffer();
        //用于缓存执行sql的临时结果集
        //有序Map
        HashMap<String, Set<String>> tempMap = new HashMap<>();
        // 从表达式的第一个字符开始处理
        for (int i = 0; i < numStr.length(); i++) {
            char ch = numStr.charAt(i); // 获取一个字符
            if (!isSign(ch)) { // 若当前字符不是符号
                temp.append(ch); // 加入到条件缓存中
            } else { // 非数字的情况
                String tempStr = temp.toString(); // 将条件缓存转为字符串
                if (!tempStr.isEmpty()) {
                    numberStack.push(tempStr); // 将条件压栈
                    temp = new StringBuffer(); // 重置条件缓存
                }
                // 判断运算符的优先级，若当前优先级低于栈顶的优先级，则先把前面计算出来
                while (!comparePri(ch) && !symbolStack.empty()) {
                    String b = numberStack.pop(); // 出栈，取出条件，后进先出
                    String a = numberStack.pop();
                    // 取出运算符进行相应运算，并把结果压栈进行下一次运算
                    switch ((char) symbolStack.pop()) {
                        case '&':
                            Set<String> set1 = this.calculator(b,tempMap);
                            Set<String> set2 = this.calculator(a,tempMap);
                            String key = "UUID_"+UUID.randomUUID();
                            set1.retainAll(set2);
                            tempMap.put(key, set1);
                            numberStack.push(key);
                            if(a.contains("UUID_")){
                                tempMap.remove(a);
                            }
                            if(b.contains("UUID_")){
                                tempMap.remove(b);
                            }
                            break;
                        case '|':
                            Set<String> set3 = this.calculator(b,tempMap);
                            Set<String> set4 = this.calculator(a,tempMap);
                            String keyOr = "UUID_"+ UUID.randomUUID();
                            set3.addAll(set4);
                            tempMap.put(keyOr, set3);
                            numberStack.push(keyOr);
                            if(a.contains("UUID_")){
                                tempMap.remove(a);
                            }
                            if(b.contains("UUID_")){
                                tempMap.remove(b);
                            }
                            break;
                        default:
                            break;
                    }
                } // while循环结束
                if (ch != '#') {
                    symbolStack.push(new Character(ch)); // 符号入栈
                    if (ch == ')') { // 去括号
                        symbolStack.pop();
                        symbolStack.pop();
                    }
                }
            }
        } // for循环结束

        return tempMap.get(numberStack.pop().toString()); // 返回计算结果
    }



    //根据传入的字符串进行运算并返回结果集
    public Set<String> calculator(String resultset,Map<String,Set<String>> tempMap) throws Exception{
        Set<String> tempSet;
        if(resultset.contains("UUID_")){
            tempSet =  tempMap.get(resultset);
        }else{
            //根据列名查表名
            String columnName = extractColomnName(resultset);
            StringBuffer getTableName = new StringBuffer("select table_name from table_dictionary ");
            getTableName.append("where column_name=");
            getTableName.append("\'");
            getTableName.append(columnName);
            getTableName.append("\'");
            ResultSet tableName = Connect_Greenplum.query(getTableName.toString());
            List tableList = Connect_Greenplum.resultSetToList(tableName, "table_name");
            if(NonUtil.isNon(tableList)){
                throw new RuntimeException("字段不存在");
            }
            StringBuffer queryResult = new StringBuffer("select * from ");
            queryResult.append(listToString(tableList, ' '));
            queryResult.append(" where ");
            queryResult.append(resultset);
            ResultSet result = Connect_Greenplum.query(queryResult.toString());
            List resultList = Connect_Greenplum.resultSetToList(result, null);
            tempSet = new HashSet(resultList);
        }

        return tempSet;
    }



    /**
     * 去除字符串中的所有空格
     */
    private String removeStrSpace(String str) {
        return str != null ? str.replaceAll(" ", "") : "";
    }

    /**
     * 检查算术表达式的基本合法性，符合返回true，否则false
     */
    private boolean isStandard(String numStr) {
        if (numStr == null || numStr.isEmpty()) // 表达式不能为空
            return false;
        Stack<Character> stack = new Stack<Character>(); // 用来保存括号，检查左右括号是否匹配
        boolean b = false; // 用来标记'#'符号是否存在多个
        for (int i = 0; i < numStr.length(); i++) {
            char n = numStr.charAt(i);
            // 判断字符是否合法
            if ( !(isChar(n) || isNumber(n) || "(".equals(n + "") || ")".equals(n + "")
                    || ">".equals(n + "") || "<".equals(n + "")
                    || "&".equals(n + "") || "|".equals(n + "")
                    || "=".equals(n + "") || "#".equals(n + "")
                    || "_".equals(n + "") || "!".equals(n + "")
                    || "-".equals(n + "") || ".".equals(n + "")
                    || "'".equals(n + ""))) {
                return false;
            }
            // 将左括号压栈，用来给后面的右括号进行匹配
            if ("(".equals(n + "")) {
                stack.push(n);
            }
            if (")".equals(n + "")) { // 匹配括号
                if (stack.isEmpty() || !"(".equals((char) stack.pop() + "")) // 括号是否匹配
                    return false;
            }
            // 检查是否有多个'#'号
            if ("#".equals(n + "")) {
                if (b)
                    return false;
                b = true;
            }
        }
        // 可能会有缺少右括号的情况
        if (!stack.isEmpty())
            return false;
        // 检查'#'号是否不在末尾
        if (!("#".equals(numStr.charAt(numStr.length() - 1) + "")))
            return false;
        return true;
    }

    /**
     * 判断一个字符是否是 ( ) $ |等符号
     */
    private boolean isSign(char c) {
        if (c == '(' || c == ')' || c == '&' || c == '|' || c == '#') {
            return true;
        } else {
            return false;
        }
    }

    //提取列名
    private String extractColomnName(String str){
        //去掉比较运算符
        if(str.indexOf("<")!=-1)
            str = str.substring(0,str.indexOf("<"));
        if(str.indexOf("=")!=-1)
            str = str.substring(0,str.indexOf("="));
        if(str.indexOf(">")!=-1)
            str = str.substring(0,str.indexOf(">"));
        return str;
    }

    /**
     * 判断一个字符是否是 a-z  A-Z 之间的字母
     */
    private boolean isChar(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 判断字符是否是0-9的数字
     */
    private boolean isNumber(char num) {
        if (num >= '0' && num <= '9')
            return true;
        return false;
    }

    /**
     * 比较优先级：如果当前运算符比栈顶元素运算符优先级高则返回true，否则返回false
     */
    private boolean comparePri(char symbol) {
        if (symbolStack.empty()) { // 空栈返回ture
            return true;
        }

        // 符号优先级说明（从高到低）:
        // 第1级: (
        // 第2级: $
        // 第3级: |
        // 第4级: )

        char top = symbolStack.peek(); // 查看堆栈顶部的对象，注意不是出栈
        if (top == '(') {
            return true;
        }
        // 比较优先级
        switch (symbol) {
            case '(': // 优先级最高
                return true;
            case '&': {
                if (top == '|') // 优先级比|高
                    return true;
                else
                    return false;
            }
            case '|': {
                return false;
            }
            case ')': // 优先级最低
                return false;
            case '#': // 结束符
                return false;
            default:
                break;
        }
        return true;
    }

    //List转成String
    public String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    // 测试
    public static void main(String args[]) throws Exception {
        //a>=20 & (b<=40|c!=1) | d
        //id<=5&(id<=3|id<=5)|(amt=45.00|id<=6)
        //id<=5|id<=5&amt=45.00|id<=6
        String num = "(date='2013-02-22'&date='2013-03-01')|id<=3&amt>=500.00"; // 默认的算式
        Analysis_zk analysis = new Analysis_zk();
        Set<String> resultMap = analysis.caculate(num);

        logger.debug(resultMap);
    }
}