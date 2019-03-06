package com.advance.JDBC;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * @Author: 谷天乐
 * @Date: 2019/2/12 16:34
 * @Description:
 */
public class Connect_GBase {
    private static Logger logger = Logger.getLogger(Connect_GBase.class);

    //三大核心接口
    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    public static Connection connectGBase() {
        //加载MySql的驱动类
        try {
            Class.forName("com.gbase.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace();
        }
        //URL
        String url = "jdbc:gbase://192.168.94.140:5258/power?characterEncoding=utf8";
        //账号
        String username = "root";
        //密码
        String password = "123456";
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            logger.error("数据库连接失败！");
            e.printStackTrace();
        }
        return conn;
    }

    //关闭数据库连接
    public static  void closeConnection(){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(pstmt!=null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Author 谷天乐
     * @Description 将ResultSet转换成List
     * @Date 2019/2/13 10:31
     * @Param [rs]
     * @return java.util.List
     **/
    public static List resultSetToList(ResultSet rs,String columnName) throws java.sql.SQLException {
        if (rs == null)
            return Collections.EMPTY_LIST;
        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等
        int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数
        List list = new ArrayList();
        Map rowData;
        while (rs.next()) {
            rowData = new HashMap(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            if(columnName==null) {
                list.add(rowData);
            }else {
                list.add(rowData.get(columnName));
            }
        }
        return list;
    }

    /**
     * @Author 谷天乐
     * @Description 查询方法
     * @Date 2019/2/13 10:32
     * @Param [sql]
     * @return java.sql.ResultSet
     **/
    public static ResultSet query(String sql) throws SQLException, ClassNotFoundException {
        conn = connectGBase();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        return rs;
    }

    /**
     * @Author 谷天乐
     * @Description 支持insert,delete,update操作
     * 参数按顺序和占位符对应传入
     * demo:
     *  update("update aa set name = ? where id = ?",new Object[]{"7","1"});
        update("insert into aa (id,name) values (?,?)",new Object[]{"5","6"});
        update("delete from aa where id = ?",new Object[]{"5"});
     * @Date 2019/2/13 10:32
     * @Param [sql, values]
     * @return void
     **/
    public static void update(String sql,Object []values) throws SQLException, ClassNotFoundException {
        //获取数据库链接
        conn=connectGBase();
        try {
            //预编译
            pstmt=conn.prepareStatement(sql);
            //获取ParameterMetaData()对象
            ParameterMetaData pmd=pstmt.getParameterMetaData();
            //获取参数个数
            int number=pmd.getParameterCount();
            //循环设置参数值
            for (int i = 1; i <=number; i++) {
                pstmt.setObject(i, values[i-1]);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author 谷天乐
     * @Description 输出查询结果
     * @Date 2019/2/13 10:33
     * @Param [rs]
     * @return void
     **/
    public static void output(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等
        int columnCount = md.getColumnCount();
        while(rs.next())
        {
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i <= columnCount; i++) {
                sb.append(rs.getObject(i)+" ");
            }
            logger.debug(sb);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //update("update aa set name = ? where id = ?",new Object[]{"7","1"});
        //update("insert into aa (id,namee) values (?,?)",new Object[]{"6","待执行"});
        //update("delete from aa where id = ?",new Object[]{"5"});
        ResultSet rs =  query("select * from fd48112019020001800220190304 ");
        //List<Map<String,Object>> result = resultSetToList(rs);
        output(rs);
        closeConnection();
    }
}