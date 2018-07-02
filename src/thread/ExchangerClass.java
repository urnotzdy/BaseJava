package thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:用于实现两个线程之间的数据交换
 * User: zhangdanyang
 * Date: 2018/6/20 11:32.
 */
public class ExchangerClass {

    public static void main(String[] args){
        ExecutorService executorService= Executors.newCachedThreadPool();
        final Exchanger<String> exchanger=new Exchanger<String>();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String data="thread1";
                try {
                    System.out.println("线程"+Thread.currentThread().getName()+"交换前的数据："+data);
                    Thread.sleep((long)Math.random()*1000);
                    String data1=exchanger.exchange(data);
                    System.out.println("线程"+Thread.currentThread().getName()+"交换后的数据："+data1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String data="thread2";
                try {
                    System.out.println("线程"+Thread.currentThread().getName()+"交换前的数据："+data);
                    Thread.sleep((long)Math.random()*1000);
                    String data1=exchanger.exchange(data);
                    System.out.println("线程"+Thread.currentThread().getName()+"交换后的数据："+data1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
