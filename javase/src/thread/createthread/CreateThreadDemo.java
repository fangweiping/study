package thread.createthread;

import java.util.concurrent.*;

/**
 * 创建线程的四种方式:
 * 1.继承Thread, 不推荐使用 ,耦合度高,线程类即是任务类,java中只能单继承,继承Thread创建线程有局限性
 *
 * 2.实现Runable接口, 推荐, 线程任务和线程类分离
 *
 * 3.实现Callable接口,创建有返回结果的线程
 *
 * 4.通过线程池创建线程, Executors(线程池工具类)可以创建四种线程池.
 */
public class CreateThreadDemo {
    public static void main(String[] args) throws Exception {
        //1. 继承Thread
        //2.实现Runable
        new Thread(() -> System.out.println("实现Runable接口创建线程")).start();
        //Thread类的start()方法底层最终还是调用的run(), Runable接口的run方法不能抛异常

        //3. 实现callable接口,调用的是call方法,可以抛异常
        Callable<String> task = new Callable<String>() {
            @Override
            public String call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    System.out.println("第" + i + "次");
                }
                return "循环结束";
            }
        };


        //启动实现Callable接口的线程类的3种方式:
        //(1) 直接调用call()方法
        String call = task.call();
        //(2)使用FutureTask执行任务,获取线程执行结果 , FutureTask是Futrue接口的实现类
        FutureTask future = new FutureTask(task);
        future.run();
        //(3)使用线程池启动
        Executors.newCachedThreadPool().submit(task);

        //4.通过线程池创建线程,线程池创建好之后,提交任务即可
        Executors.newCachedThreadPool();

    }
}
