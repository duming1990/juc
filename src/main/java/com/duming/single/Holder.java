package com.duming.single;

public class Holder {
    private Holder(){

    }

    public static Holder getInstance(){
        return InnerClass.holder;
    }

    public static class InnerClass{
        private static  final Holder holder=new Holder();
    }
}
