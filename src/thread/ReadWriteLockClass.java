package thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description:读写锁。读锁与读锁不互斥，读锁与写锁互斥，写锁与写锁互斥
 * 三个读线程，三个写线程
 * User: zhangdanyang
 * Date: 2018/6/13 18:18.
 */
public class ReadWriteLockClass {

    public static void main(String[] args){
        Queue queue=new Queue();
        for(int i=0;i<2;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    queue.set(new Random().nextInt(1000));
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    queue.get();
                }
            }).start();
        }
    }

}
class Queue{
    int data;
    ReadWriteLock lock=new ReentrantReadWriteLock();
    public void get(){
        lock.readLock().lock();
        //读数据
        try {
            System.out.println(Thread.currentThread().getName() + " is ready to read data！");
            System.out.println(Thread.currentThread().getName() + " has read data:" + data);
        }finally {
            lock.readLock().unlock();
        }
    }

    public void set(int data){
        lock.writeLock().lock();
        //写数据
        try {
            System.out.println(Thread.currentThread().getName() + " is ready to write data！");
            this.data = data;
            System.out.println(Thread.currentThread().getName() + " has writed data:" + data);
        }finally {
            lock.writeLock().unlock();
        }
    }

}
