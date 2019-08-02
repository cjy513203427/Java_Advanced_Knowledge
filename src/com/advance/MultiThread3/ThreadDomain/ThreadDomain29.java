package com.advance.MultiThread3.ThreadDomain;

public class ThreadDomain29 {
    private final Object obj1 = new Object();
    private final Object obj2 = new Object();

    public void obj1obj2() throws Exception
    {
        synchronized (obj1)
        {
            Thread.sleep(2000);
            synchronized (obj2)
            {
                System.out.println("obj1obj2 end!");
            }
        }
    }

    public void obj2obj1() throws Exception
    {
        synchronized (obj2)
        {
            Thread.sleep(2000);
            synchronized (obj1)
            {
                System.out.println("obj2obj1 end!");
            }
        }
    }

}
