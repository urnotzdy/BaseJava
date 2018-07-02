package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:lock锁的使用
 * User: zhangdanyang
 * Date: 2018/6/13 17:45.
 */
public class LockClass {

    public static void main(String[] args){
        Output output=new Output();
        for (int i=0;i<20;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    output.out("zhangdanyang");
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    output.out("liuzhiming");
                }
            }).start();
        }
    }
}

class Output{
    Lock lock=new ReentrantLock();
    public void out(String s){
        int len=s.length();
        lock.lock();
        try {
            for (int i = 0; i < len; i++) {
                System.out.print(s.charAt(i));
            }
            System.out.println();
        }finally {
            lock.unlock();
        }
    }
}
