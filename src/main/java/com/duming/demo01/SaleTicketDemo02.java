package com.duming.demo01;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
    正真的多线程开发，公司中的开发，降低耦合
    线程就是一个单独的资源类，没有任何附属的操作！
 */
public class SaleTicketDemo02{
    public static void main(String[] args) {
        //并发：多线程操作同一个资源类，把资源丢入数据
        ticket2 ticket = new ticket2();

        //@FunctionalInterface 函数式接口，jdk1.8 lambda表达式（参数）->{代码}
        new Thread(()-> {for (int i = 0; i < 40; i++)ticket.sale();},"thread_01").start();

        new Thread(()-> {for (int i = 0; i < 40; i++)ticket.sale();},"thread_02").start();

        new Thread(()-> {for (int i = 0; i < 40; i++)ticket.sale();},"thread_03").start();
    }
}

//lock三部曲
//1.new ReentrantLock()
//2.lock.lock();//加锁
//3.finally->lock.unlock();解锁
class ticket2{
    //属性，方法
    private int number=30;

    Lock lock=new ReentrantLock();
    //卖票的方式
    public void  sale(){
        lock.lock();//加锁

//        lock.tryLock();

        try {
            if (number >0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票,剩余："+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}