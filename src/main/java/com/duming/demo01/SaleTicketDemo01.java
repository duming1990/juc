package com.duming.demo01;
/*
    正真的多线程开发，公司中的开发，降低耦合
    线程就是一个单独的资源类，没有任何附属的操作！
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        //并发：多线程操作同一个资源类，把资源丢入数据
        ticket ticket = new ticket();

        //@FunctionalInterface 函数式接口，jdk1.8 lambda表达式（参数）->{代码}
        new Thread(()-> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        },"thread_01").start();

        new Thread(()-> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        },"thread_02").start();

        new Thread(()-> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        },"thread_03").start();
    }
}

//资源类 OOP
class ticket{
    //属性，方法
    private int number=50;

    //卖票的方式
    public synchronized void  sale(){
        if (number >0) {
            System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票,剩余："+number);
        }
    }
}