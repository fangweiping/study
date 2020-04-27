package thread.createthread;

public class ThredDemo {
    public static void main(String[] args) throws InterruptedException {
        //  保证三个线程顺序执行
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"执行");
            }
        };
        Thread tA = new Thread(task,"A线程");
        Thread tB = new Thread(task,"B线程");
        Thread tC = new Thread(task,"C线程");
        /*thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。
            比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。
            此处即是将ABC 三个线程都加入到主线程 (main)之中,通过调用子线程的join()方法来阻塞主线程达到
            ABC三个线程串行执行,即保证了顺序执行
        */
            tA.start();
            tA.join();
            tB.start();
            tB.join();
            tC.start();
            tC.join();
    }
}
