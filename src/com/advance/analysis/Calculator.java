/**  
* TODO(用一句话描述该文件做什么)
* @company Finedo.cn
* @author zhangkuo
* @date 2019年2月12日 下午4:41:26
* @version V1.0  
*/
package com.advance.analysis;

import com.advance.JDBC.Connect_Greenplum;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class Calculator {

		private static Logger logger = Logger.getLogger(Calculator.class);
	    /** 条件栈：用于存储表达式中的各个条件 */
	    private Stack<String> numberStack = null;
	    /** 符号栈：用于存储运算符和括号 */
	    private Stack<Character> symbolStack = null;

	    /**
	     * 解析并计算规则表达式(含括号)，返回计算结果
	     * 
	     * @param numStr
	     *            规则表达式(含括号)
	     */
	    public Map caculate(String numStr) throws SQLException, ClassNotFoundException {
			//用于缓存执行sql的临时结果集
			Map<String,Collection> tempMap = new HashMap<>();
	        numStr = removeStrSpace(numStr); // 去除空格
	        // 如果规则表达式尾部没有‘#’号，则在尾部添加‘#’，表示结束符
	        if (numStr.length() > 1 && !"#".equals(numStr.charAt(numStr.length() - 1) + "")) {
	            numStr += "#";
	        }
	        // 检查表达式是否合法
	        if (!isStandard(numStr)) {
				logger.error("错误：规则表达式有误！");
	            return null;
	        }
	        // 初始化栈
	        numberStack = new Stack<String>();
	        symbolStack = new Stack<Character>();
	        // 用于缓存条件，因为条件可能是多位的
	        StringBuffer temp = new StringBuffer();
	        // 从表达式的第一个字符开始处理
	        for (int i = 0; i < numStr.length(); i++) {
				String a;
				String b;
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
						a = numberStack.pop();
						b = numberStack.pop(); // 出栈，取出条件，后进先出
	                    // 取出运算符进行相应运算，并把结果压栈进行下一次运算

	                    switch ((char) symbolStack.pop()) {
	                    	//以'$1'结尾表示单字段
							case '$':
								if(a.indexOf("Calculation")==-1) {
									//根据列名查表名
									//先出栈的数字无意义，所以取b
									String bColumnName= extractColomnName(b);
									String getTableSql = "select table_name from table_dictionary " +
											"where column_name="+"\'"+bColumnName+"\'" ;
									ResultSet tableNameRS = Connect_Greenplum.query(getTableSql);
									List tableList = Connect_Greenplum.resultSetToList(tableNameRS,"table_name");
									//对每个条件进行执行，把执行出来的结果进行求交集操作，把结果放到栈中
									String selectAndSql1 = "select * from "+listToString(tableList,' ')+" where " + b;
									ResultSet rsAnd1 = Connect_Greenplum.query(selectAndSql1);
									List resultAndList1 = Connect_Greenplum.resultSetToList(rsAnd1,null);
									//求交集
									tempMap.put("SingleResult", resultAndList1);
									numberStack.push("And Calculation is over");
								}
								break;
							case '&':
								if(a.indexOf("Calculation")==-1||b.indexOf("Calculation")==-1) {
									//根据列名查表名
									String aColumnName = extractColomnName(a);
									String bColumnName= extractColomnName(b);
									String getTableSqlA = "select table_name from table_dictionary " +
											"where column_name=" + "\'" + aColumnName + "\'";
									ResultSet tableNameRSA = Connect_Greenplum.query(getTableSqlA);
									List tableListA = Connect_Greenplum.resultSetToList(tableNameRSA, "table_name");
									String getTableSqlB = "select table_name from table_dictionary " +
											"where column_name=" + "\'" + bColumnName + "\'";
									ResultSet tableNameRSB = Connect_Greenplum.query(getTableSqlB);
									List tableListB = Connect_Greenplum.resultSetToList(tableNameRSB, "table_name");
									//对每个条件进行执行，把执行出来的结果进行求交集操作，把结果放到栈中
									String selectAndSql1 = "select * from " + listToString(tableListA, ' ') + " where " + a;
									ResultSet rsAnd1 = Connect_Greenplum.query(selectAndSql1);
									String selectAndSql2;
									ResultSet rsAnd2 = null;
									//位于前面的表达式不能为空
									if (b.indexOf("Calculation") == -1){
										selectAndSql2 = "select * from " + listToString(tableListB, ' ') + " where " + b;
										rsAnd2 = Connect_Greenplum.query(selectAndSql2);
									}
									List resultAndList1;
									//如果上一步有&(交集)运算，取出这个list
									if(tempMap.get("AndResult")!=null) {
										resultAndList1 = Connect_Greenplum.resultSetToList(rsAnd1, null);
										if(symbolStack.peek()=='|'){
											if(!numStr.substring(0,numStr.indexOf("&")).contains("|")){
												resultAndList1.addAll(tempMap.get("AndResult"));
											}else
											{
												resultAndList1.retainAll(tempMap.get("AndResult"));
											}
										}else{
											resultAndList1.retainAll(tempMap.get("AndResult"));
										}

									}
									//第一次取交集运算
									else{
										resultAndList1 = Connect_Greenplum.resultSetToList(rsAnd1, null);
									}
									List resultAndList2 = Connect_Greenplum.resultSetToList(rsAnd2,null);
									//resultAndList2不可以为空，这里的为空是指长度为0，列名输入错误导致字典表找不到表
									//查询失败导致的空集，这样交集是空集（非法）
									//如果列和表都存在，查出来的list为空，则合法，可以参与交集运算，结果为空集
									if(resultAndList2.size()!=0)
										resultAndList1.retainAll(resultAndList2);
									tempMap.put("AndResult", resultAndList1);
									numberStack.push("And Calculation is over");
								}
								break;
							case '|':
									//根据列名查表名
									String aColumnName= extractColomnName(a);
									String bColumnName= extractColomnName(b);
									String getTableSqlA = "select table_name from table_dictionary " +
											"where column_name=" + "\'" + aColumnName + "\'";
									ResultSet tableNameRSA = Connect_Greenplum.query(getTableSqlA);
									String getTableSqlB = "select table_name from table_dictionary " +
											"where column_name="+"\'"+bColumnName+"\'" ;
									List tableListA = Connect_Greenplum.resultSetToList(tableNameRSA,"table_name");
									ResultSet tableNameRSB = Connect_Greenplum.query(getTableSqlB);
									List tableListB = Connect_Greenplum.resultSetToList(tableNameRSB,"table_name");
									//对每个条件进行执行，把执行出来的结果进行求并集操作，把结果放到栈中
									String selectOrSql1;
									ResultSet rsOr1 = null;
									if (a.indexOf("Calculation") == -1) {
										selectOrSql1 = "select * from " + listToString(tableListA, ' ') + " where " + a;
										rsOr1 = Connect_Greenplum.query(selectOrSql1);
									}
									String selectOrSql2;
									ResultSet rsOr2 = null;
									if (b.indexOf("Calculation") == -1) {
										selectOrSql2 = "select * from " + listToString(tableListB, ' ') + " where " + b;
										rsOr2 = Connect_Greenplum.query(selectOrSql2);
									}
									List resultOrList1;
									//如果上一步有|(并集)运算，取出这个list
									if(tempMap.get("OrResult")!=null) {
										resultOrList1 = Connect_Greenplum.resultSetToList(rsOr1, null);
										if (resultOrList1.size()!=0){
											resultOrList1.addAll(tempMap.get("OrResult"));
										}else{
											resultOrList1 = new ArrayList();
											resultOrList1.addAll(tempMap.get("OrResult"));
										}
									}
									//第一次取并集运算
									else{
										resultOrList1 = Connect_Greenplum.resultSetToList(rsOr1, null);
									}

									List resultOrList2 = Connect_Greenplum.resultSetToList(rsOr2,null);
									if(resultOrList2.size()==0)
										resultOrList2 = new ArrayList();
									List andResult = (List) tempMap.get("AndResult");
									if(andResult==null)
										andResult = new ArrayList();
									//a.addAll(b),a为空会@throws UnsupportedOperationException
									if(resultOrList1.size()==0){
										resultOrList2.addAll(resultOrList1);
										resultOrList2.addAll(andResult);
										tempMap.put("OrResult", resultOrList2);
									}else{
										resultOrList1.addAll(resultOrList2);
										resultOrList1.addAll(andResult);
										tempMap.put("OrResult", resultOrList1);
										tempMap.remove("AndResult");
									}
									numberStack.push("Or Calculation is over");

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
	        List orResult = (List) tempMap.get("OrResult");
	        List finalResult = new ArrayList();
			if(orResult!=null)
				finalResult.addAll(orResult);
	        tempMap.put("FinalResult",finalResult);
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
						|| "'".equals(n + "") || "$".equals(n + ""))) {
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
	     * 判断一个字符是否是 '(',')'等符号
	     */
	    private boolean isSign(char c){
	    	if(c == '(' || c == ')' ||c == '#' || c == '&' || c == '|' || c == '$'){
	    		return true;
	    	}else{
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
	    private boolean isChar(char c){
	    	if(( c >= 'a' && c <= 'z' )||( c >= 'A' && c <= 'Z' )){
	    		return true;
	    	}else{
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
	        if (symbolStack.empty()) { // 空栈返回true
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
				case '$'://单字段结束符
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
	    public static void main(String args[]) throws SQLException, ClassNotFoundException {
	    	//( id<=5 & id<=4) & (date = '2013-06-01' | c1<=2)

			//( id<=5$1 ) 解决单字段查询，以'$1'作标记结尾
			//( id<=6 & id<=7 & amt=500.00 & id<=8) 解决2个以上的'&'
			//( id<=5 | id<=3 | amt=45.00|amt=500.00) 解决2个以上的'|
			//( id<=5& id<=7&id<=5|id<=3) 解决2个以上的连续'&'和结尾是'|'
			//( id<=3| id<=3|id<=5&id<=3) 解决2个以上连续的'|'和结尾是'&'
			//( id<=5& id<=3|id<=7|id<=5|id<=3)解决开头是'&'和结尾是'|'
			//待解决开头是'|',结尾是'&' ( id<=5| id<=3&id<=5&id<=7)
			//( id<=5&id<=7& id<=3|id<=5&id<=3) 解决多个'&',中间'|'
			//( id<=3|id<=5| id<=3&id<=5|id<=3) 解决多个'|',中间'&'
			//待解决'&'和'|'混合  ( id<=5& id<=7|id<=5&id<=3)
	        String num = " ( id>=5&id<=7|id<=5&id<=3)"; // 默认的算式
	        Calculator calculator = new Calculator();
	        Map result = calculator.caculate(num);
			logger.debug(result);
	    }
}
