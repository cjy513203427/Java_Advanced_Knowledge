package com.advance.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/15 09:01
 * @Description:
 */
public class Dos {
    private Logger logger = LogManager.getLogger(Dos.class);
    /**
     * @Author 谷天乐
     * @Description 读取命令的输出结果
     * @Date 2019/3/15 9:10
     * @Param [pr]
     * @return void
     **/
    public void read(Process pr) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream(), "GBK"));
        String line;
        while ((line = input.readLine()) != null) {
            //logger.debug(line);
        }
    }

    @Test
    public void exp() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("exp scott/tiger@orcl file=e:\\opt\\oracle_output\\daochu.dmp tables=(buy_cnt_c1)");
            read(pr);
            int exitVal = pr.waitFor();
            logger.debug("Exited with error code " + exitVal);

        } catch (Exception e) {
           logger.error(e.toString());
            e.printStackTrace();
        }
    }

    @Test
    public void imp() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("imp scott/tiger@orcl file=e:\\opt\\oracle_output\\daochu.dmp");
            read(pr);
            int exitVal = pr.waitFor();
            logger.debug("Exited with error code " + exitVal);

        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
    }



    @Test
    public void sqluldr2() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("sqluldr264 scott/tiger@127.0.0.1/orcl query=\"select * from buy_cnt_c1\" head=yes file=e:\\opt\\oracle_output\\tmp001.csv");
            read(pr);
            int exitVal = pr.waitFor();
            logger.debug("Exited with error code " + exitVal);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
    }

    @Test
    public void sqlldr() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("sqlldr userid=scott/tiger control=e:/opt/oracle_ctl/tmp002.ctl log=e:/opt/oracle_ctl/tmp001.log data=e:/opt/oracle_output/tmp001.csv rows=64");
            read(pr);
            int exitVal = pr.waitFor();
            logger.debug("Exited with error code " + exitVal);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
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
        exp();
        imp();
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
        sqluldr2();
        sqlldr();
    }

    @Test
    public void testcmd() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("cmd /c ping www.baidu.com && dir");
            read(pr);
            int exitVal = pr.waitFor();
            logger.debug("Exited with error code " + exitVal);

        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
    }
}