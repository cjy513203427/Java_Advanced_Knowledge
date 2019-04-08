package com.advance.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/15 09:01
 * @Description:
 */
public class OracleInputOutput {
    private static Logger logger = LogManager.getLogger(OracleInputOutput.class);

    private static OSinfo oSinfo = new OSinfo();

    //exp scott/tiger@orcl file=e:\opt\oracle_output\daochu.dmp tables=(buy_cnt_c1)
    //exp scott/tiger file=output/daochu.dmp tables='(buy_cnt_c1)'
    public static void exp(String username, String password, String connection, String filePath, String tableName) {
        if(checkFilePath(filePath)) {
            try {
                if (oSinfo.isWindows()) {
                    new CommandUtil().excute("exp " + username + "/" + password + "@" + connection + " file=" + filePath + " tables=" + tableName + "");
                } else if (oSinfo.isLinux()) {
                    new CommandUtil().excute("exp " + username + "/" + password + "@" + connection + " file=" + filePath + " tables=" + "\'" + tableName + "\'" + "");
                }
            } catch (Exception e) {
                logger.error(e.toString());
                e.printStackTrace();
            }
        }
    }

    //imp scott/tiger@orcl file=e:\opt\oracle_output\daochu.dmp
    //imp scott/tiger@orcl11g file=input/daoru.cmp
    public static void imp(String username, String password, String connection, String filePath) {
        if(checkFilePath(filePath)) {
            try {
                new CommandUtil().excute("imp " + username + "/" + password + "@" + connection + "  file=" + filePath);
            } catch (Exception e) {
                logger.error(e.toString());
                e.printStackTrace();
            }
        }
    }


    //sqluldr264 scott/tiger@127.0.0.1/orcl query="select * from buy_cnt_c1" head=yes file=e:\opt\oracle_output\tmp001.csv
    //sqluldr2_linux64_10204.bin user=scott/tiger@orcl11g query="select * from buy_cnt_c1" head=yes file=output/tmp002.csv
    public static void sqluldr2(String username, String password, String connection, String querySql, String filePath) {
        if(checkFilePath(filePath)) {
            try {
                if (oSinfo.isWindows()) {
                    new CommandUtil().excute("sqluldr264 " + username + "/" + password + "@" + connection + " query=" + "\"" + querySql + "\"" + " head=yes file=" + filePath);
                } else if (oSinfo.isLinux()) {
                    new CommandUtil().excute("sqluldr2_linux64_10204.bin " + username + "/" + password + "@" + connection + " query=" + "\"" + querySql + "\"" + " head=yes file=" + filePath);
                }
            } catch (Exception e) {
                logger.error(e.toString());
                e.printStackTrace();
            }
        }
    }

    //sqlldr userid=scott/tiger control=e:/opt/oracle_ctl/tmp002.ctl data=e:/opt/oracle_output/tmp001.csv rows=64
    //sqlldr userid=scott/tiger control=input/tmp002.ctl log=input/log/tmp002.log data=input/tmp001.csv rows=64
    public static void sqlldr(String username, String password, String ctlFilePath, String filePath) {
        if (checkFilePath(filePath) && checkFilePath(filePath)) {
            try {
                new CommandUtil().excute("sqlldr " + username + "/" + password + " control=" + ctlFilePath + " data=" + filePath + " rows=64");
            } catch (Exception e) {
                logger.error(e.toString());
                e.printStackTrace();
            }
        }
    }

    //"e:/opt/oracle_output"
    //"/opt/oracle_output"
    public static boolean checkFilePath(String content){
        boolean isMatch = false;
        if(oSinfo.isWindows()) {
            String pattern = "[a-zA-Z]:(\\\\.*|/.*)";
            isMatch = Pattern.matches(pattern, content);
        }else if(oSinfo.isLinux()) {
            String pattern = "(/\\w*)*";
            isMatch = Pattern.matches(pattern, content);
        }else{
            logger.debug("未知的操作系统文件路径");
        }

        return isMatch;
    }

    /**
     * @Author 谷天乐
     * @Description 测试exp和imp传统的导入导出执行时间
     * 8293条数据大约546ms
     * 2123008条数据大约8s100ms
     * 8492032条数据大约25s796ms
     * @Date 2019/3/15 10:54
     * @Param []
     * @return void
     **/
    @Test
    public void exp_imp(){
        //exp();
        //imp();
    }

    /**
     * @Author 谷天乐
     * @Description 测试sqluldr2和sqlldr文件式导入导出执行时间
     * 8293条数据大约610ms
     * 2123008条数据大约6s378ms
     * 8492032条数据大约2m6s54ms
     * @Date 2019/3/15 10:55
     * @Param []
     * @return void
     **/
    @Test
    public void sqluldr2_sqlldr(){
//        sqluldr2();
//        sqlldr();
    }

    @Test
    public void testcmd() {
        new CommandUtil().excute("cmd /c ping www.baidu.com");
    }

    public static void main(String[] args) {
        //exp scott/tiger@orcl file=e:\opt\oracle_output\daochu.dmp tables=(buy_cnt_c1)
        exp("scott","tiger", "orcl", "e:\\opt\\oracle_output\\daochu.dmp","buy_cnt_test");
        //imp scott/tiger@orcl file=e:\opt\oracle_output\daochu.dmp
        //("scott","tiger","orcl","e:\\opt\\oracle_output\\daochu.dmp");
        //sqluldr264 scott/tiger@127.0.0.1/orcl query="select * from buy_cnt_c1" head=yes file=e:\opt\oracle_output\tmp001.csv
        //sqluldr2("scott", "tiger", "127.0.0.1/orcl", "select * from buy_cnt_test", "e:\\opt\\oracle_output\\tmp001.csv");
        //sqlldr userid=scott/tiger control=e:/opt/oracle_ctl/tmp002.ctl data=e:/opt/oracle_output/tmp001.csv rows=64
        //sqlldr("scott", "tiger", "e:/opt/oracle_ctl/tmp002.ctl", "e:/opt/oracle_ctl/tmp001.csv");
    }
}