/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/5/22 10:08.
 */
public class ThreadForJ {

    public static void main(String[] args){
        new ThreadForJ().handleJ();
    }

    public void handleJ(){
        HandleClass handleClass=new HandleClass();
        for(int i=0;i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"线程加1为："+handleClass.add());
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"线程减1为："+handleClass.desc());
                }
            }).start();
        }
    }

    static class HandleClass{

        private boolean flag=true;
        private int j=0;

        public synchronized int add(){
            while (!flag){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //加1
            j=j+1;
            flag=false;
            this.notify();
            return j;
        }

        public synchronized int desc(){
            while (flag){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //减1
            j=j-1;
            flag=true;
            this.notify();
            return j;
        }

    }

}
