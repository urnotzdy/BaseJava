package thread;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description:相同的key互斥
 * User: zhangdanyang
 * Date: 2018/6/21 18:05.
 */
public class Test3_1 extends Thread{

    TestDo1 testDo1;
    String key;
    String value;

    public Test3_1(String key,String key2,String value){
        this.testDo1=TestDo1.getInstance();
        this.key=key+key2;
        this.value=value;
    }

    @Override
    public void run() {
        testDo1.doSome(key,value);
    }

    public static void main(String[] args){
        Test3_1 a=new Test3_1("1","","1");
        Test3_1 b=new Test3_1("1","","2");
        Test3_1 c=new Test3_1("3","","3");
        Test3_1 d=new Test3_1("4","","4");
        System.out.println("begin:"+(System.currentTimeMillis()/1000));
        a.start();
        b.start();
        c.start();
        d.start();
    }

}
class TestDo1{
    private TestDo1(){};
    private static TestDo1 instance=new TestDo1();
//    private List<Object> list=new ArrayList<Object>();
    private CopyOnWriteArrayList<Object> list=new CopyOnWriteArrayList<>();
    public static TestDo1 getInstance(){
        return instance;
    }
    public void doSome(Object key,String value){
        Object o=key;
        if(list.contains(key)){
            for(Object k:list){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(k.equals(o)){
                    o=k;
                }
            }
        }else{
            list.add(key);
        }
        synchronized (o){
            try {
                Thread.sleep(1000);
                System.out.println(key+":"+value+":"+(System.currentTimeMillis()/1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}