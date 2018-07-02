package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:线程1通知线程2，线程2通知线程3，线程3通知线程1.使用condition
 * User: zhangdanyang
 * Date: 2018/6/14 17:02.
 */
public class ThreeConditionLockClass {

    public static void main(String[] args){
        ConditionCommuniteClass conditionCommuniteClass=new ConditionCommuniteClass();
        for(int i=0;i<50;i++){
            final int num=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    conditionCommuniteClass.fun1(num);
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    conditionCommuniteClass.fun2(num);
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    conditionCommuniteClass.fun3(num);
                }
            }).start();
        }
    }

    static class ConditionCommuniteClass{
        Lock lock=new ReentrantLock();
        Condition condition1=lock.newCondition();
        Condition condition2=lock.newCondition();
        Condition condition3=lock.newCondition();
        int flag=1;
        public void fun1(int num){
            lock.lock();
            try {
                while (flag!=1) try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) System.out.println("one thread " + num + " is looping for " + i);
                flag=2;
                condition2.signal();
            }finally {
                lock.unlock();
            }
        }
        public void fun2(int num){
            lock.lock();
            try {
                while (flag!=2) try {
                    condition2.await();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                for (int i = 0; i < 20; i++) System.out.println("two thread " + num + " is looping for " + i);
                flag=3;
                condition3.signal();
            }finally {
                lock.unlock();
            }
        }
        public void fun3(int num){
            lock.lock();
            try {
                while (flag!=3)
                    try {
                        condition3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                for (int i = 0; i < 20; i++) System.out.println("three thread " + num + " is looping for " + i);
                flag=1;
                condition1.signal();
            }finally {
                lock.unlock();
            }
        }
    }

}
