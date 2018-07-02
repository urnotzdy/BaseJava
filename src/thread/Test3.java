package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/6/21 16:51.
 */
public class Test3 extends Thread{

    BlockingQueue<String> synchronousQueue;
    TestDo testDo;
    String key;
    String value;

    public Test3(String key,String key2,String value,BlockingQueue<String> synchronousQueue){
        this.testDo=TestDo.getInstance();
        this.key=key+key2;
        this.value=value;
        this.synchronousQueue=synchronousQueue;
    }

    @Override
    public void run() {
        try {
            if(synchronousQueue.contains(key)){
                Thread.sleep(1000);
            }else {
                synchronousQueue.put(key);
            }
            testDo.doSome(key,value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        BlockingQueue<String> bq=new ArrayBlockingQueue<String>(4);
        Test3 a=new Test3("1","","1",bq);
        Test3 b=new Test3("1","","2",bq);
        Test3 c=new Test3("3","","3",bq);
        Test3 d=new Test3("4","","4",bq);
        System.out.println("begin:"+(System.currentTimeMillis()/1000));
        a.start();
        b.start();
        c.start();
        d.start();
    }
}
class TestDo{
    private TestDo(){};
    private static TestDo instance=new TestDo();
    public static TestDo getInstance(){
        return instance;
    }
    public void doSome(Object key,String value){
        try {
            Thread.sleep(1000);
            System.out.println(key+":"+value+":"+(System.currentTimeMillis()/1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

