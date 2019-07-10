package createthread;

public class ThredDemo1 {

    public static void main(String[] args) throws InterruptedException {

        //  保证三个线程顺序执行
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"线程执行");
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);














    }
}
