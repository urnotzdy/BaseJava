package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:jdk中condition的使用实例，类似于生产者和消费者的模式
 * User: zhangdanyang
 * Date: 2018/6/14 16:09.
 */
public class ConditionLock2Class {

    public static void main(String[] args){
        BounderBuffer bounderBuffer=new BounderBuffer();
        for(int i=0;i<100;i++){
            final int num=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bounderBuffer.put(num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            try {
                System.out.println(num+"线程获取数据"+bounderBuffer.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class BounderBuffer{
        Lock lock=new ReentrantLock();
        Condition notFull=lock.newCondition();//不满的锁
        Condition notEmpty=lock.newCondition();//不空的锁
        int[] buffer=new int[10];
        int count=0;//放入与取走的数量
        int putI=0;//放入的坐标
        int getI=0;//得到的坐标
        public void put(int x) throws InterruptedException {
            lock.lock();
            try {
                if (count == buffer.length) {
                    //代表已经放满了，需等待
                    notFull.await();
                }
                buffer[putI] = x;
                if (++putI == buffer.length) putI = 0;
                ++count;
                //放置完成
                notEmpty.signal();
            }finally {
                lock.unlock();
            }
        }
        public int get() throws InterruptedException {
            lock.lock();
            try {
                if (count == 0) {
                    //已经取完了，等待着放
                    notEmpty.await();
                }
                int x = buffer[getI];
                if (++getI == buffer.length) getI = 0;
                --count;
                //已经取出数据
                notFull.signal();
                return x;
            }finally {
                lock.unlock();
            }
        }
    }

}
