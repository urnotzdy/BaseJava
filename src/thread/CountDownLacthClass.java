package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:一犹如倒计时计数器，个人等待其他所有人来通知他，或者多个人等待一个人来公布
 * User: zhangdanyang
 * Date: 2018/6/20 10:42.
 */
public class CountDownLacthClass {

    public static void main(String[] args){
        ExecutorService executorService= Executors.newCachedThreadPool();
        final CountDownLatch orderLatch=new CountDownLatch(1);
        final CountDownLatch answerLatch=new CountDownLatch(3);
        for(int i=0;i<3;i++){
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程"+Thread.currentThread().getName()+"正在等待接收命令");
                        orderLatch.await();
                        System.out.println("线程"+Thread.currentThread().getName()+"已接受命令");
                        answerLatch.countDown();
                        System.out.println("线程"+Thread.currentThread().getName()+"回应命令处理结果");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);
        }
        try {
            Thread.sleep((long) Math.random()*1000);
            System.out.println("线程"+Thread.currentThread().getName()+"即将发送命令");
            orderLatch.countDown();
            System.out.println("线程"+Thread.currentThread().getName()+"等待回应");
            answerLatch.await();
            System.out.println("线程"+Thread.currentThread().getName()+"已收到回应");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
