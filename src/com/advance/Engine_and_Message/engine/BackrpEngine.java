package com.advance.Engine_and_Message.engine;

import com.advance.Engine_and_Message.domain.Message;
import com.advance.Engine_and_Message.domain.TaskInfo;
import com.advance.Engine_and_Message.util.NonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/7 17:31
 * @Description:
 */
public class BackrpEngine extends Engine{
    List<String> cyclerpArr =new ArrayList<>();

    @Override
    protected void execute() throws Exception {
        //从线程池获取线程
        Long threadid=Thread.currentThread().getId();
        //线程池加入该线程
        Threadmanager.addThreadToPool(threadid);
        //初始化测试List
        Integer messagedesc = getMessage().getMessagedesc();
        while(NonUtil.isNotNon(messagedesc)){
            System.out.println("线程："+threadid+"的任务长度："+messagedesc);
            messagedesc--;
            if(messagedesc==0) {
                System.out.println(getClass().getName() +"线程："+threadid+ "业务处理成功");
                break;
            }
            TimeUnit.SECONDS.sleep(1);
        }



    }

}
