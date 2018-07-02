package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:阻塞队列，可以代替lock、condition
 * User: zhangdanyang
 * Date: 2018/6/20 14:49.
 */
public class BlockingQueueClass {

    public static void main(String[] args){
        ExecutorService executorService= Executors.newCachedThreadPool();
        Communication communication=new Communication();
        for(int i=0;i<10;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    communication.thread1();
                }
            });
            communication.thread2();
        }
    }

    static class Communication{
        BlockingQueue bq1=new ArrayBlockingQueue(1);
        BlockingQueue bq2=new ArrayBlockingQueue(1);
        {
            try {
                bq2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void thread1(){
            try {
                bq1.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0;i<10;i++){
                System.out.println("线程"+Thread.currentThread().getName()+"循环次数为"+i);
            }
            try {
                bq2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void thread2(){
            try {
                bq2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0;i<30;i++){
                System.out.println("线程"+Thread.currentThread().getName()+"循环次数为"+i);
            }
            try {
                bq1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
