package thread;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//生产者与消费者
public class ProduceAndConsumer {

    public static void main(String[] args) {
        BlockingQueue queue=new ArrayBlockingQueue(10);
        Producer p=new Producer(queue);
        Thread pro=new Thread(p);
        pro.start();
        Thread con=new Thread(new Consumer(queue));
        con.start();
    }

    public static class Producer implements Runnable{
        BlockingQueue queue=null;
        int task=0;
        public Producer(BlockingQueue queue){
            this.queue=queue;
        }
        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("生产者生产一个任务："+task);
                    queue.put(task++);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public static class Consumer implements Runnable{
        BlockingQueue queue=null;
        int task=0;
        public Consumer(BlockingQueue queue){
            this.queue=queue;
        }
        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("消费者消费一个任务："+queue.take());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
