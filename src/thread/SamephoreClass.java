package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Description:有10个任务，但是只有3个可以并发
 * User: zhangdanyang
 * Date: 2018/6/14 18:12.
 */
public class SamephoreClass {

    public static void main(String[] args){
        ExecutorService executorService= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(3);
        for(int i=0;i<10;i++){
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程"+Thread.currentThread().getName()+"进入，当前并发数为"+(3-semaphore.availablePermits()));
                    try {
                        Thread.sleep((long)(Math.random()*1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程"+Thread.currentThread().getName()+"即将离开");
                    semaphore.release();
                    System.out.println("线程"+Thread.currentThread().getName()+"已经离开，当前并发数为"+(3-semaphore.availablePermits()));
                }
            };
            executorService.execute(runnable);
        }

    }


}
