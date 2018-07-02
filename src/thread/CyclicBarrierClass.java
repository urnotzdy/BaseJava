package thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:所有的线程集合，然后处理下一个问题
 * User: zhangdanyang
 * Date: 2018/6/20 9:43.
 */
public class CyclicBarrierClass {

    public static void main(String[] args){
        ExecutorService executorService= Executors.newCachedThreadPool();
        final CyclicBarrier cyclicBarrier=new CyclicBarrier(3);
        for(int i=0;i<3;i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                        System.out.println("即将到达集合地点1，当前已有"+(cyclicBarrier.getNumberWaiting()+1)+"个线程到达");
                        cyclicBarrier.await();
                        Thread.sleep((long) (Math.random() * 1000));
                        System.out.println("即将到达集合地点2，当前已有"+(cyclicBarrier.getNumberWaiting()+1)+"个线程到达");
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();

    }

}
