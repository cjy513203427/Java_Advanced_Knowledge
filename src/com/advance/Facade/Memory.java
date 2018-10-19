package com.advance.Facade;

import com.sun.istack.internal.logging.Logger;

/**
 * Memory子系统类
 * @author Administrator
 *
 */
public class Memory
{
    public static final Logger LOGGER = Logger.getLogger(Memory.class);
    public void start()
    {
        LOGGER.info("Memory is start...");
    }

    public void shutDown()
    {
        LOGGER.info("Memory is shutDown...");
    }
}