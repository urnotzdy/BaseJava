package thread;

/**
 * Description:设计4个线程，其中两个线程每次对j增加1，另外两个线程对j每次减少1
 * User: zhangdanyang
 * Date: 2018/6/11 17:43.
 */
public class MultiThreadClass {
//    private static OprationJ2 oprationJ=new OprationJ2();
    private static OprationJ oprationJ=new OprationJ();
    public static void main(String[] args){
        for (int i=0;i<2;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    oprationJ.inc();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    oprationJ.dec();
                }
            }).start();
        }
    }
}
//对同一个变量进行加减
class OprationJ{
    int j=0;
    public synchronized void inc(){
        for(int i=0;i<5;i++) {
            j++;
            System.out.println(Thread.currentThread().getName()+"加1：" + j);
        }
    }
    public synchronized void  dec(){
        for(int i=0;i<5;i++) {
            j--;
            System.out.println(Thread.currentThread().getName()+"减1：" + j);
        }
    }

}
//使用wait和notify来保证加减交替进行，同步进行。使用wait和notify将共享资源加锁
class OprationJ2{
    int j=0;
    boolean flag=true;//为true，则加1
    public synchronized void inc() {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        j++;
        System.out.println(Thread.currentThread().getName()+"加1：" + j);
        notify();
        flag=false;
    }
    public synchronized void dec(){
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        j--;
        System.out.println(Thread.currentThread().getName()+"减1：" + j);
        notify();
        flag=true;
    }
}