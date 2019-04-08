package com.advance.command;

/**
 * 平台
 * @author isea533
 */
public enum EPlatform {
    Linux("Linux"),
    Windows("Windows");

    private EPlatform(String desc){
        this.description = desc;
    }

    public String toString(){
        return description;
    }

    private String description;
}