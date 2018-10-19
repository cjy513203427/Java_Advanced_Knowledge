package com.advance.Facade;

import com.sun.istack.internal.logging.Logger;

/**
 * 客户端类
 * @author Administrator
 *
 */
public class Cilent {
    public static final Logger LOGGER = Logger.getLogger(Cilent.class);
    public static void main(String[] args)
    {
        Computer computer = new Computer();
        computer.start();
        LOGGER.info("=================");
        computer.shutDown();
    }

}