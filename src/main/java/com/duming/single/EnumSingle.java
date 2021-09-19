package com.duming.single;

//enum 本身也是一个class类
public enum  EnumSingle {
    INSTANCE;

    public EnumSingle getInstance(){
        return  INSTANCE;
    }

}
