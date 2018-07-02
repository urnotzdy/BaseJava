package thread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/6/20 18:27.
 */
public class Test2 {

    public static void main(String[] args){
//        BlockingQueue<String> b1=new ArrayBlockingQueue<String>(1);
//        Semaphore semaphore=new Semaphore(1);
        Lock lock=new ReentrantLock();
        SynchronousQueue<String> synchronousQueue=new SynchronousQueue<String>();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
//                        semaphore.acquire();
                        lock.lock();
                        String data = synchronousQueue.take();
                        String output = TestDo.doSome(data);
                        System.out.println(Thread.currentThread().getName() + ":" + output);
//                        semaphore.release();
                        lock.unlock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        System.out.println("begin:"+System.currentTimeMillis()/1000);
        for(int i=0;i<10;i++){
            String input=i+"";
            try {
                synchronousQueue.put(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class TestDo {
        public static String doSome(String input) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String output=input+":"+System.currentTimeMillis()/1000;
            return output;
        }
    }
}
