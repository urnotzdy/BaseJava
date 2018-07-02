package thread;

/**
 * Description:wait和notify的使用实例
 * User: zhangdanyang
 * Date: 2018/6/14 15:20.
 */
public class WaitNotifyClass {

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
        boolean childFlag=true;
        public synchronized void childLoop(int m){
            while (!childFlag){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i=0;i<10;i++){
                System.out.println("child thread "+m+" is looping "+i);
            }
            childFlag=false;
            this.notify();
        }
        public synchronized void mainLoop(int m){
            while (childFlag){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i=0;i<100;i++){
                System.out.println("main thread "+m+" is looping "+i);
            }
            childFlag=true;
            this.notify();
        }
    }
}
