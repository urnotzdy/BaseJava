package thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Description:关于线程返回结果的处理callable和future
 * User: zhangdanyang
 * Date: 2018/6/12 14:38.
 */
public class CallableAndFutureClass {

    public static void main(String[] args){
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        Future<String> future=
            executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "Hello";
                }
            });
        System.out.println("等待结果");
        try {
            System.out.println("得到结果："+future.get());//会一直等到拿到结果
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        //使用CompletionService提交一组Callable任务。其task方法放回一个已经完成的Callable任务对应的Future对象
        ExecutorService executorService1=Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService=new ExecutorCompletionService<Integer>(executorService1);
        for(int i=0;i<10;i++) {
            final int seq=i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return seq;
                }
            });
        }
        for(int i=0;i<10;i++){
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService1.shutdown();
    }

}
