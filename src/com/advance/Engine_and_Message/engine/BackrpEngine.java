package com.advance.Engine_and_Message.engine;

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
        //从哪里获取的线程？？
        Long threadid=Thread.currentThread().getId();
        //线程池加入该线程
        Threadmanager.addThreadToPool(threadid);
        //如果获取数据失败时，等待增加20次循环等待，每次等待5分钟
        cyclerpArr.add("C");
        cyclerpArr.add("C++");
        cyclerpArr.add("Java");
        cyclerpArr.add("Python");
        cyclerpArr.add("Lua");

        int length = cyclerpArr.size();
        int times = 1;
        while(NonUtil.isNotNon(cyclerpArr)){
            for (int i=0;i<length;i++ ) {
                System.out.println(cyclerpArr);
            }
            length--;
            System.out.println(getClass().getName()+"业务处理成功");
            if(times>=20){
                if(NonUtil.isNon(cyclerpArr)){
                    throw new NullPointerException(getClass().getName()+"获取数据失败1");
                }
            }
            TimeUnit.SECONDS.sleep(5);
            times++;
        }



    }

}
