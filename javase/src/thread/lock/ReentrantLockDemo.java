package thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {

        //默认非公平锁
        ReentrantLock lock = new ReentrantLock();

        //加锁
        lock.lock();
        try {

            //尝试获取锁，成功返回true,失败返回false
            lock.tryLock();

            //指定时间内尝试获取锁，成功返回true,失败返回false,如果当前线程被中断抛出InterruptedException
            lock.tryLock(1L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
    }

}
