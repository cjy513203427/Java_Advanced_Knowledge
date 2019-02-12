package com.advance.JDBC;

import org.apache.log4j.Logger;

import java.sql.*;

/**
 * @Author: 谷天乐
 * @Date: 2019/2/12 16:34
 * @Description:
 */
public class Connect_GBase {
    private static Logger logger = Logger.getLogger(Connect_GBase.class);

    public static void main(String[] args){
        try{
            //加载MySql的驱动类
            Class.forName("com.gbase.jdbc.Driver");
            String url = "jdbc:gbase://192.168.94.140:5258/power?user=root" ;
            Connection con = DriverManager.getConnection(url);
            String sql = "select * from aa ";
            PreparedStatement pstmt = con.prepareStatement(sql) ;
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                logger.info(rs.getObject(1)+" "+rs.getObject(2));
            }
        }catch(SQLException se){
            System.out.println("数据库连接失败！");
            se.printStackTrace() ;
        }catch(ClassNotFoundException e){
            System.out.println("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace() ;
        } finally {
            System.exit(0);
        }
    }
}