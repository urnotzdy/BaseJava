package thread;

import java.util.Random;

/**
 * Description:获取每个线程中的数据
 * User: zhangdanyang
 * Date: 2018/6/12 9:55.
 */
public class ThreadLocalClass {
    private static ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>();
    public static void main(String[] args){
        for(int i=0;i<2;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int num=new Random().nextInt();
                    System.out.println("线程"+Thread.currentThread().getName()+"产生数据："+num);
                    threadLocal.set(num);//放到当前线程中
                    new A().get();
                }
            }).start();
        }
    }
    static class A{
        public void get(){
            //从当前线程中获取数据
            System.out.println("线程"+Thread.currentThread().getName()+"获取数据："+threadLocal.get());
        }
    }

}
