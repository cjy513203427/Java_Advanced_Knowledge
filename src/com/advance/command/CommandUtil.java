package com.advance.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/25 16:35
 * @Description:获取命令行所有执行结果
 */
public class CommandUtil
{
    private static Logger logger = LogManager.getLogger(CommandUtil.class);

    public static void excute(String commandStr){
        try
        {
            Process process = Runtime.getRuntime ().exec (commandStr);
            SequenceInputStream sis = new SequenceInputStream (process.getInputStream (), process.getErrorStream ());
            InputStreamReader isr = new InputStreamReader (sis, "gbk");
            BufferedReader br = new BufferedReader (isr);
            // next command
            OutputStreamWriter osw = new OutputStreamWriter (process.getOutputStream ());
            BufferedWriter bw = new BufferedWriter (osw);
            String line = null;
            StringBuffer output = new StringBuffer();
            while (null != ( line = br.readLine () ))
            {
                output.append(line+"\n");
            }
            System.out.println(output);
            process.destroy ();
            br.close ();
            isr.close ();
        }
        catch (IOException e)
        {
            e.printStackTrace ();
        }
    }

    public static void main ( String[] args )
    {
        excute("ping www.baidu.com");

    }
}