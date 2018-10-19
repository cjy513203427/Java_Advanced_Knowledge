package com.advance.Facade;

import com.sun.istack.internal.logging.Logger;

/**
 * 门面类（核心）
 * @author Administrator
 *
 */
public class Computer
{
    public static final Logger LOGGER = Logger.getLogger(Computer.class);

    private CPU cpu;
    private Memory memory;
    private Disk disk;
    public Computer()
    {
        cpu = new CPU();
        memory = new Memory();
        disk = new Disk();
    }
    public void start()
    {
        LOGGER.info("Computer start begin");
        cpu.start();
        disk.start();
        memory.start();
        LOGGER.info("Computer start end");
    }

    public void shutDown()
    {
        LOGGER.info("Computer shutDown begin");
        cpu.shutDown();
        disk.shutDown();
        memory.shutDown();
        LOGGER.info("Computer shutDown end...");
    }
}