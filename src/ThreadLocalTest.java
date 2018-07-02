import java.util.Random;

/**
 * Description:线程范围内的变量共享
 * User: zhangdanyang
 * Date: 2018/5/22 9:29.
 */
public class ThreadLocalTest {


    public static void main(String[] args){
        new ThreadLocalTest().test();
    }

    public void test(){
        for(int i=0;i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int num=new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()+"线程产生随机数："+num);
                    ThreadLocalClass.getInstance().setNum(num);
                    new Obj().soutData(Thread.currentThread().getName());
                }
            }).start();
        }
    }

    class Obj{
        public void soutData(String name){
            System.out.println(ThreadLocalClass.getInstance().getNum());
        }
    }

    static class ThreadLocalClass{

        public static ThreadLocal<ThreadLocalClass> threadLocal=new ThreadLocal<ThreadLocalClass>();
        private ThreadLocalClass(){}

        public static ThreadLocalClass getInstance(){
            ThreadLocalClass threadLocalClass=threadLocal.get();
            if(threadLocalClass==null){
                threadLocalClass=new ThreadLocalClass();
                threadLocal.set(threadLocalClass);
            }
            return threadLocalClass;
        }

        private int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

}
