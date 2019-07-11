package com.advance.ftp_sftp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 
 * @Title:  PropertiesUtil.java   
 * @Package cn.finedo.dsp_service.service.util   
 * @Description: 读取Properties文件信息
 * @author: chenjinyao     
 * @date:   2019-1-9 上午11:02:11
 */
public class PropertiesUtil {
	private static Logger logger = LogManager.getLogger(PropertiesUtil.class);
    
   /**
    * 
    * @Title: printAllProperty   
    * @Description: 输出
    * @Author: chenjinyao
    * @param: @param props      
    * @return: void      
    * @throws
    */
    private static void printAllProperty(Properties props)
    {  
        @SuppressWarnings("rawtypes")  
        Enumeration en = props.propertyNames();  
        while (en.hasMoreElements())  
        {  
            String key = (String) en.nextElement();  
            String value = props.getProperty(key);
            logger.info("{} : {}",key,value);
        }
    }
    
    /**
     * 
     * @Title: getProperties   
     * @Description: 项目为根目录,如src/jdbc.properties
     * 				 暂不支持相对路径写法，如../../../jdbc.properties，会报FileNotFound
     * @Author: chenjinyao
     * @param: @param filePath
     * @param: @param keyWord
     * @param: @return      
     * @return: String      
     * @throws
     */
    public static String getProperties(String filePath, String keyWord){
        Properties prop = new Properties();
        String value = null;
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
            // 通过输入缓冲流进行读取配置文件
            //InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
            // 加载输入流
            prop.load(inputStream);
            // 根据关键字获取value值
            value = prop.getProperty(keyWord);
        } catch (Exception e) {
        	logger.error("获取Properties指定key失败",e);
        }
        return value;
    }
    
    /**
     * 
     * @Title: getProperties   
     * @Description: 读取全部属性
     * @Author: chenjinyao
     * @param: @param filePath      
     * @return: void      
     * @throws
     */
    public static void getProperties(String filePath){
        Properties prop = new Properties();
        try {
            // 通过输入缓冲流进行读取配置文件
            InputStream InputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
            // 加载输入流
            prop.load(InputStream);
            printAllProperty(prop);
        } catch (Exception e) {
            logger.error("读取Properties全部属性失败",e);
        }
    }
    
    
    
    public static void main(String[] args) throws Exception {
        PropertiesUtil.getProperties("src\\com\\advance\\ftp\\sftp.properties","ftpHost");
        //logger.debug("dsp_greenplum.jdbc.driverClassName = {}" + ftpHost);
        logger.debug("*********************************************");
        
        //String username = new String(DES.decryptString("ylE+qa5tlAoxWBWavaFJSQ=="));
        //String usernmaee = username.substring(0,username.length()-3);
        //logger.debug(usernmaee);
    }
}