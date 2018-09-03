package jvm;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class ThreadMXBeanTest {

    //监控当前有多少线程
    @Test
    public void testThreadMXBean() throws InterruptedException {
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread");
            }
        });
        t.start();
        t.join();
        ThreadMXBean threadMXBean=ManagementFactory.getThreadMXBean();
        ThreadInfo[] threads= threadMXBean.dumpAllThreads(false,false);
        for(ThreadInfo threadInfo:threads){
            System.out.println(threadInfo.getThreadId()+":"+threadInfo.getThreadName());
        }
    }

}
