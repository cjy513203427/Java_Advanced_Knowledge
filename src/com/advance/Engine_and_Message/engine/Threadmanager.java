package com.advance.Engine_and_Message.engine;

import com.advance.Engine_and_Message.domain.ThreadManagerDomain;
import com.advance.Engine_and_Message.util.NonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/8 08:57
 * @Description:
 * 线程管理类
 */
public class Threadmanager {
    private static Map<Long,ThreadManagerDomain> threadpool= new HashMap<>();

    /**
     * 增加线程到线程池
     * @param Threadid
     */
    public static void addThreadToPool(Long Threadid){
        ThreadManagerDomain threadManagerDomain=new ThreadManagerDomain(Threadid,new Date());
        threadpool.put(Threadid, threadManagerDomain);
        System.out.println("线程:"+Threadid+"加入，线程池总大小："+threadpool.size());
    }

    /**
     * 线程正常停止，移除线程池相应线程。
     * @param threadid
     */
    public static Boolean removeThreadToPool(Long threadid){
        ThreadManagerDomain threadManagerDomain=threadpool.get(threadid);
        if(NonUtil.isNotNon(threadManagerDomain)){
            threadpool.remove(threadid);
            System.out.println("线程:"+threadid+"移除，总大小："+threadpool.size());
            return true;
        }
        return false;
    }

    /**
     * 手动杀死线程
     * @param threadid
     */
    @SuppressWarnings("deprecation")
    public static Map<String,Object> stopThread(Long threadid){
        Map ret = new HashMap();
        ThreadManagerDomain threadManagerDomain=threadpool.get(threadid);
        if(NonUtil.isNon(threadManagerDomain)){
            ret.put("Success","任务停止成功");
            return ret;
        }
        Thread thread=findThread(threadManagerDomain.getThreadID());
        try{
            thread.stop();
        }catch(Exception e){
            ret.put("Fail","任务停止失败");
            return ret;
        }finally{
            if(!thread.isAlive()){
                removeThreadToPool(threadid);
            }
        }
        ret.put("Success","任务停止成功");
        return ret;
    }
    /**
     * 批量杀死线程
     * @param threadids
     */
    public static Map<String,Object> batchstopThread(Long[] threadids){
        Map ret = new HashMap();
        for (Long threadid : threadids) {
            stopThread(threadid);
        }
        ret.put("Success","任务停止成功");
        return ret;
    }


    //查找线程id
    private static Thread findThread(long threadId) {
        Map<Thread, StackTraceElement[]> map=Thread.getAllStackTraces(); //也可以
        Iterator<Thread> it=map.keySet().iterator();
        while (it.hasNext()) {
            Thread thread=it.next(); //
            if(thread.getId()==threadId){
                return thread;
            }
        }
        return null;
    }

}
