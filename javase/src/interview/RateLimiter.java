package interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class RateLimiter {

    private long intervalMilliSecond;

    private Semaphore semaphore;

    public RateLimiter(int permitsPerSecond) throws IllegalAccessException {
        if (permitsPerSecond <= 0) {
            throw new IllegalAccessException("非法参数");
        }
        semaphore = new Semaphore(1);
        intervalMilliSecond = 1000L / permitsPerSecond;
    }

    public void acquire() throws InterruptedException {
        semaphore.acquire();
        Thread.currentThread().sleep(intervalMilliSecond);
        semaphore.release();
    }

    public static void main(String[] args) throws Exception {
        RateLimiter rateLimiter = new RateLimiter(10);
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            es.submit(() -> {
                //处理请求
                try {
                    rateLimiter.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "处理完成");
            });
        }
        es.shutdown();
        if (!es.awaitTermination(60, TimeUnit.SECONDS)) {
            es.shutdownNow();
        }
    }
}
