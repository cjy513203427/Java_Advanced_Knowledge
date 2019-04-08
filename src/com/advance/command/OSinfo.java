package com.advance.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 操作系统类：
 * 获取System.getProperty("os.name")对应的操作系统
 * @author isea533
 */
public class OSinfo {
    private static Logger logger = LogManager.getLogger(OSinfo.class);

    private static String OS = System.getProperty("os.name").toLowerCase();

    private static OSinfo _instance = new OSinfo();

    private EPlatform platform;

    public OSinfo(){}

    public static boolean isLinux(){
        return OS.indexOf("linux")>=0;
    }

    public static boolean isWindows(){
        return OS.indexOf("windows")>=0;
    }
    /**
     * 获取操作系统名字
     * @return 操作系统名
     */
    public static EPlatform getOSname(){
        if(isLinux()){
            _instance.platform = EPlatform.Linux;
        }else if (isWindows()) {
            _instance.platform = EPlatform.Windows;
        }
        return _instance.platform;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        logger.debug(OSinfo.getOSname());
    }

}