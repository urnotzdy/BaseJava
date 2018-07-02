package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:lock锁中的condition使用实例
 * User: zhangdanyang
 * Date: 2018/6/14 15:33.
 */
public class ConditionLockClass {

    public static void main(String[] args){
        LoopClass loopClass=new LoopClass();
        for(int i=0;i<50;i++){
            final int num=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    loopClass.childLoop(num);
                }
            }).start();
            loopClass.mainLoop(num);
        }
    }
    static class LoopClass{
        Lock lock=new ReentrantLock();
        Condition condition=lock.newCondition();
        boolean childFlag=true;
        public void childLoop(int m){
            lock.lock();
            try {
                while (!childFlag) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println("child thread " + m + " is looping " + i);
                }
                childFlag = false;
                condition.signal();
            }finally {
                lock.unlock();
            }
        }
        public void mainLoop(int m){
            lock.lock();
            try {
                while (childFlag) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 100; i++) {
                    System.out.println("main thread " + m + " is looping " + i);
                }
                childFlag = true;
                condition.signal();
            }finally {
                lock.unlock();
            }
        }
    }

}
