package com.advance.Facade;

import com.sun.istack.internal.logging.Logger;

/**
 * cpu子系统类
 * @author Administrator
 *
 */
public class CPU
{
    public static final Logger LOGGER = Logger.getLogger(CPU.class);
    public void start()
    {
        LOGGER.info("cpu is start...");
    }

    public void shutDown()
    {
        LOGGER.info("CPU is shutDown...");
    }
}