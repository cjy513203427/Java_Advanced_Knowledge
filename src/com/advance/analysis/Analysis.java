package com.advance.analysis;
import com.advance.JDBC.Connect_GBase;
import com.advance.Redis.RedisUtil;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @Author: 谷天乐
 * @Date: 2019/2/18 19:32
 * @Description: 修正版
 */
public class Analysis {
    public String UUID_PREFIX = "UUID_";

    public String UUID_PREFIX_AND = "UUID_And_";

    public String UUID_PREFIX_OR = "UUID_Or_";

    private static Logger logger = Logger.getLogger(Analysis.class);
    /**
     * 条件栈：用于存储表达式中的各个条件
     */
    private Stack<String> numberStack = null;
    /**
     * 符号栈：用于存储运算符和括号
     */
    private Stack<Character> symbolStack = null;

    /**
     * 解析并计算规则表达式(含括号)，返回计算结果
     *
     * @param numStr 规则表达式(含括号)
     */
    public LinkedHashMap<String, Set<Map>> caculate(String numStr) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        numStr = removeStrSpace(numStr); // 去除空格
        // 如果规则表达式尾部没有‘#’号，则在尾部添加‘#’，表示结束符
        if (numStr.length() > 1 && !"#".equals(numStr.charAt(numStr.length() - 1) + "")) {
            numStr += "#";
        }
        // 检查表达式是否合法
        if (!isStandard(numStr)) {
            System.err.println("错误：规则表达式有误！");
            return null;
        }
        // 初始化栈
        numberStack = new Stack<String>();
        symbolStack = new Stack<Character>();
        // 用于缓存条件，因为条件可能是多位的
        StringBuffer temp = new StringBuffer();
        //用于缓存执行sql的临时结果集
        //有序Map
        LinkedHashMap<String, Set<Map>> tempMap = new LinkedHashMap<>();
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
                            if(a.contains(UUID_PREFIX)&&b.contains(UUID_PREFIX)){
                                bothHaveUUID(tempMap,"and");
                            }else if(!a.contains(UUID_PREFIX)&&b.contains(UUID_PREFIX)){
                                optionalUUID(tempMap,a,UUID_PREFIX_AND,"and");
                            }else if(a.contains(UUID_PREFIX)&&!b.contains(UUID_PREFIX)){
                                optionalUUID(tempMap,b,UUID_PREFIX_AND,"and");
                            } else {
                                bothDontHaveUUID(a,b,tempMap,UUID_PREFIX_AND,"and");
                            }
                            break;

                        case '|':
                            //根据列名查表名
                            if(a.contains(UUID_PREFIX)&&b.contains(UUID_PREFIX)){
                                bothHaveUUID(tempMap,"or");
                            }else if(!a.contains(UUID_PREFIX)&&b.contains(UUID_PREFIX)){
                                optionalUUID(tempMap,a,UUID_PREFIX_OR,"or");
                            }else if(a.contains(UUID_PREFIX)&&!b.contains(UUID_PREFIX)){
                                optionalUUID(tempMap,b,UUID_PREFIX_OR,"or");
                            }else {
                                bothDontHaveUUID(a,b,tempMap,UUID_PREFIX_OR,"or");
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

        //使用entrySet会@throws ConcurrentModificationException
        //清缓存：去掉Map中无用的Set，计算过程中的Set在a含有'UUID'，b不含有'UUID'，
        //b含有'UUID'，a不含有'UUID'的情况被清除
        //清除最终运算结果，即a和b都含有'UUID'，a和b都不含有'UUID'的情况
        Set<Map.Entry<String, Set<Map>>> entries = tempMap.entrySet();
        Iterator<Map.Entry<String, Set<Map>>> iteratorMap = entries.iterator();
        while (iteratorMap.hasNext()){
            Map.Entry<String, Set<Map>> next = iteratorMap.next();
            if(getTail(tempMap).getKey()!=next.getKey())
                iteratorMap.remove();
        }
        return tempMap; // 返回计算结果
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

        char top = (char) symbolStack.peek(); // 查看堆栈顶部的对象，注意不是出栈
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

/*JDK1.8    public static <K, V> Map.Entry<K, V> getTailByReflection(LinkedHashMap<K, V> map)
            throws NoSuchFieldException, IllegalAccessException {
        Field tail = map.getClass().getDeclaredField("tail");
        tail.setAccessible(true);
        return (Map.Entry<K, V>) tail.get(map);
    }*/

    public <K, V> Map.Entry<K, V> getTail(LinkedHashMap<K, V> map) {
        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
        Map.Entry<K, V> tail = null;
        while (iterator.hasNext()) {
            tail = iterator.next();
        }
        return tail;
    }

    /**
     * @Author 谷天乐
     * @Description a和b都含有'UUID_'
     * @Date 2019/2/25 11:25
     * @Param [tempMap, andor]
     * @return void
     **/
    public void bothHaveUUID(LinkedHashMap<String, Set<Map>> tempMap,String andor){
        int num = 0;
        List keys = new ArrayList();
        for (Map.Entry<String, Set<Map>> entry : tempMap.entrySet()) {
            keys.add(entry.getKey());
            num++;
        }
        Set resultAndSet1 = tempMap.get(keys.get(num-2));
        Set resultAndSet2 = tempMap.get(keys.get(num-1));
        //求交集
        if(andor.equals("and"))
            resultAndSet1.retainAll(resultAndSet2);
        if(andor.equals("or"))
            resultAndSet1.addAll(resultAndSet2);
        String andkey = String.valueOf(UUID.randomUUID());
        andkey = "UUID_And_" + andkey.toString().replace("-", "");
        numberStack.push(andkey);
        tempMap.put(andkey, resultAndSet1);
    }


    /**
     * @Author 谷天乐
     * @Description a和b都不含有"UUID_"
     * @Date 2019/2/25 12:37
     * @Param [conditionA, conditionB, tempMap, UUID_prefix, andor]
     * @return void
     **/
    public void bothDontHaveUUID(String conditionA,String conditionB,LinkedHashMap<String, Set<Map>> tempMap,String UUID_prefix,String andor) throws SQLException, ClassNotFoundException {
        String aOrColumnName = extractColomnName(conditionA);
        String bOrColumnName = extractColomnName(conditionB);

        StringBuffer getOrTableSqlA = new StringBuffer("select table_name from table_dictionary ");
        getOrTableSqlA.append("where column_name=");
        getOrTableSqlA.append("\'");
        getOrTableSqlA.append(aOrColumnName);
        getOrTableSqlA.append("\'");
        ResultSet tableOrNameRSA = Connect_GBase.query(getOrTableSqlA.toString());
        List tableOrListA = Connect_GBase.resultSetToList(tableOrNameRSA, "table_name");
        String getOrTableSqlB = "select table_name from table_dictionary " +
                "where column_name=" + "\'" + bOrColumnName + "\'";
        ResultSet tableOrNameRSB = Connect_GBase.query(getOrTableSqlB);
        List tableOrListB = Connect_GBase.resultSetToList(tableOrNameRSB, "table_name");

        StringBuffer selectOrSql1 = new StringBuffer("select * from ");
        if(tableOrListA.size()==0)
            throw new RuntimeException("字段不存在");
        selectOrSql1.append(listToString(tableOrListA, ' '));
        selectOrSql1.append(" where ");
        selectOrSql1.append(conditionA);
        ResultSet rsOr1 = Connect_GBase.query(selectOrSql1.toString());

        StringBuffer selectOrSql2 = new StringBuffer("select * from ");
        if(tableOrListB.size()==0)
            throw new RuntimeException("字段不存在");
        selectOrSql2.append(listToString(tableOrListB, ' '));
        selectOrSql2.append(" where ");
        selectOrSql2.append(conditionB);
        ResultSet rsOr2 = Connect_GBase.query(selectOrSql2.toString());
        List resultOrList1 = Connect_GBase.resultSetToList(rsOr1, null);
        List resultOrList2 = Connect_GBase.resultSetToList(rsOr2, null);
        Set resultOrSet1 = new HashSet(resultOrList1);
        Set resultOrSet2 = new HashSet(resultOrList2);
        //求并集
        if(andor.equals("or"))
            resultOrSet1.addAll(resultOrSet2);
        if(andor.equals("and"))
            resultOrSet1.retainAll(resultOrSet2);
        //对每个条件进行执行，把执行出来的结果进行求并集操作，把结果放到栈中
        String orkey = String.valueOf(UUID.randomUUID());
        orkey = UUID_prefix + orkey.toString().replace("-", "");
        numberStack.push(orkey);
        tempMap.put(orkey, resultOrSet1);
    }

    /*
     * @Author 谷天乐
     * @Description Or 运算如下情况：
     * a含有'UU_ID',b不含有'UUID';b含有'UU_ID',a不含有'UUID'
     * @Date 2019/2/20 9:34
     * @Param [tempMap, condition]
     * @return void
     **/
    public void optionalUUID(LinkedHashMap<String, Set<Map>> tempMap,String condition,String UUID_prefix,String andor) throws SQLException, ClassNotFoundException {
        int num = 0;
        List keys = new ArrayList();
        for (Map.Entry<String, Set<Map>> entry : tempMap.entrySet()) {
            keys.add(entry.getKey());
            num++;
        }
        Set resultOrSet1 = tempMap.get(keys.get(num-1));
        //根据列名查表名
        String aOrColumnName = extractColomnName(condition);

        StringBuffer getOrTableSqlA = new StringBuffer("select table_name from table_dictionary ");
        getOrTableSqlA.append("where column_name=");
        getOrTableSqlA.append("\'");
        getOrTableSqlA.append(aOrColumnName);
        getOrTableSqlA.append("\'");
        ResultSet tableOrNameRSA = Connect_GBase.query(getOrTableSqlA.toString());
        List tableOrListA = Connect_GBase.resultSetToList(tableOrNameRSA, "table_name");
        if(tableOrListA.size()==0)
            throw new RuntimeException("字段不存在");

        StringBuffer selectOrSql2 = new StringBuffer("select * from ");
        selectOrSql2.append(listToString(tableOrListA, ' '));
        selectOrSql2.append(" where ");
        selectOrSql2.append(condition);
        ResultSet rsOr2 = Connect_GBase.query(selectOrSql2.toString());
        List resultOrList2 = Connect_GBase.resultSetToList(rsOr2, null);

        Set resultOrSet2 = new HashSet(resultOrList2);
        //求并集
        if (andor.equals("or"))
            resultOrSet1.addAll(resultOrSet2);
        if  (andor.equals("and"))
            resultOrSet1.retainAll(resultOrSet2);
        //移除使用过的Set
        tempMap.remove(keys.get(num-1));
        String orkey = String.valueOf(UUID.randomUUID());
        orkey = UUID_prefix + orkey.toString().replace("-", "");
        //对每个条件进行执行，把执行出来的结果进行求交集操作，把结果放到栈中
        numberStack.push(orkey);
        //--1、把a和b解析成sql并执行获取结果集，使用UUID生成key放入tempMap中，
        tempMap.put(orkey, resultOrSet1);
    }

    //根据Map获取指定key的value
    public Set getSpecifiedListFromMap(LinkedHashMap<String, Set<Map>> resultMap, String key,String id) throws Exception{
        Set<Object> set = new HashSet<>();

        for (Map.Entry<String, Set<Map>> entry : resultMap.entrySet()) {
            new RedisUtil().del(id);
            for(Map maplist : entry.getValue()){
                set.add(maplist.get(key));
                new RedisUtil().lpush(id,String.valueOf(maplist.get(key)));
            }
        }
        logger.debug(Analysis.class+"Jedis插入数据成功");
        return set;
    }

    @Test
    public void test() throws Exception {
        //a>=20 & (b<=40|c!=1) | d
        //id<=5&(id<=3|id<=5)|(amt=45.00|id<=6)
        //id<=5|id<=5&amt=45.00|id<=6
        //(date='2013-07-22'|date='2013-02-22')&id<=7&phone_number=18895358020
        String num = "(A_I72=1&A_I59=0)"; // 默认的算式
        Analysis analysis = new Analysis();
        LinkedHashMap<String, Set<Map>> resultMap = analysis.caculate(num);
        getSpecifiedListFromMap(resultMap,"serial_number","ssss1111");
        logger.debug(resultMap);

    }
}