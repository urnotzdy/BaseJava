package queue;

public interface Queue<Item> {

    //入列
    Item dequeue() throws Exception;

    //出列
    void enqueue(Item item);

    int size();

    boolean isEmpty();
}
