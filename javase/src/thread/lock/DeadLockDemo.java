package thread.lock;

import arithmetic.lambda.User;

/**
 * @author fwp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/04/09/ 00:40:00
 */
@SuppressWarnings("all")
public class DeadLockDemo {

    static User lockA = new User();
    static User lockB = new User();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lockA) {
                while (true) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程1获取lockB中......");
                    synchronized (lockB) {
                        System.out.println("线程1已获取lockB");
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lockB) {
                while (true) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程2获取lockA中......");
                    synchronized (lockA) {
                        System.out.println("线程2已获取lockA");
                    }
                }
            }
        }).start();


    }
}
