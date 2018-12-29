package com.advance.Engine_and_Message.domain;

import java.util.Date;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/8 08:57
 * @Description:
 */
public class ThreadManagerDomain{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long threadID;//线程id
    private Date startTime;//开始时间
    public Long getThreadID() {
        return threadID;
    }

    public ThreadManagerDomain(Long threadID, Date startTime) {
        super();
        this.threadID = threadID;
        this.startTime = startTime;
    }

    public void setThreadID(Long threadID) {
        this.threadID = threadID;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }



}