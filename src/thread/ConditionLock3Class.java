package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:两个线程，同时启动，线程A数1、2、3，然后线程B数4、5、6，最后线程A数7、8、9。
 * User: zhangdanyang
 * Date: 2018/7/24 9:47.
 */
public class ConditionLock3Class {

    public static void main(String[] args) {
        Num num=new Num();
        Lock lock=new ReentrantLock();
        Condition c1=lock.newCondition();
        Condition c2=lock.newCondition();
        Thread threadA=new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (num.index < 4) {
                        System.out.println(num.index);
                        num.inc();
                    }
                    c1.signal();
                }finally {
                    lock.unlock();
                    System.out.println("线程A_1释放锁");
                }
                lock.lock();
                try {
                    while (num.index<7)
                        c2.await();
                    while (num.index < 10) {
                        System.out.println(num.index);
                        num.inc();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("线程A_2释放锁");
                }
            }
        });
        Thread threadB=new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (num.index<4)
                        c1.await();
                    while (num.index < 7) {
                        System.out.println(num.index);
                        num.inc();
                    }
                    c2.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("线程B释放锁");
                }
            }
        });
        threadA.start();
        threadB.start();
    }

    static class Num{
        int index=1;
        public int inc(){
            return ++index;
        }
    }

}
