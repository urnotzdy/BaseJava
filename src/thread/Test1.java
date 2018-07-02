package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/6/20 17:42.
 */
public class Test1 {

    public static void main(String[] args){
        fun1();
    }
    private static void fun1(){
        BlockingQueue<String> b=new ArrayBlockingQueue<String>(1);
        for(int i=0;i<4;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String log = b.take();
                            parseTime(log);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        System.out.println("begin:"+(System.currentTimeMillis()/1000));
        for(int i=0;i<16;i++){
            String log=i+1+"";
            try {
                b.put(log);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static void fun2(){
        System.out.println("begin:"+(System.currentTimeMillis()/1000));
        ExecutorService executorService= Executors.newFixedThreadPool(4);
        for(int i=0;i<16;i++){
            String log=i+1+"";
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    parseTime(log);
                }
            });
        }
    }
    private static void parseTime(String log) {
        System.out.println(log+":"+System.currentTimeMillis()/1000);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
