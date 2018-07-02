package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:线程池：线程+线程池中的任务
 * 包括：固定大小的线程池、缓存线程池（能指定线程池大小）、只有一个线程的线程池、定时的线程池
 * User: zhangdanyang
 * Date: 2018/6/12 13:51.
 */
public class ThreadPoolClass {

    public static void main(String[] args){
        //固定大小的线程池
        ExecutorService executorService= Executors.newFixedThreadPool(3);//创建3个线程池
        for(int i=0;i<10;i++) {//创建10个任务
            final int task=i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10;j++){//每个任务执行10次循环
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("线程"+Thread.currentThread().getName()+" is looping of "+j+" from task "+task);
                    }
                }
            });
        }
        //每次只有3个任务被执行
        System.out.println("all of 10 tasks have committed!!");
        executorService.shutdown();//当线程池中的任务做完后，关闭线程

        //定时器的线程池
        Executors.newScheduledThreadPool(3).schedule(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("bombing!");
                }
            },
            10,
            TimeUnit.SECONDS);

        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("bombing!");
                    }
                },
                10,
                2,
                TimeUnit.SECONDS
        );
    }
}
