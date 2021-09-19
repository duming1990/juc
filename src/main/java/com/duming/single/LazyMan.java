package com.duming.single;

import java.lang.reflect.Constructor;

//懒汉式单例模式
public class LazyMan {
    private LazyMan() {
        synchronized (LazyMan.class){
            if(lazyMan!=null){
                throw  new RuntimeException("不要试图用反射破坏异常");
            }
        }
        System.out.println(Thread.currentThread().getName()+"ok");

    }
    private volatile static  LazyMan lazyMan;

    //双重检测锁模式的懒汉式单例 DCL懒汉式
    public static LazyMan getInstance(){
        if(null == lazyMan){
            synchronized (LazyMan.class){
                if(null == lazyMan){
                    lazyMan=new LazyMan();//不是一个原子性操作
                    /*
                     * 1.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 3.把这个对象指向这个空间
                     *
                     * 123
                     * 132 A
                     *     B //此时lazyman还没有完成构造 加上
                     */
                }
            }
        }
        return lazyMan;
    }

    //多线程并发
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//                LazyMan.getInstance();
//            }).start();
//        }
//    }

    //反射
    public static void main(String[] args) throws Exception {
        LazyMan instance  =LazyMan.getInstance();
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan instance2 = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);

    }
}
