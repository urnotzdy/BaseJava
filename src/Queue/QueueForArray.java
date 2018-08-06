package Queue;

import java.util.Iterator;

//使用数组的方式实现队列
public class QueueForArray<Item> implements Queue<Item>,Iterable<Item>{

    Item[] items;
    int first;//指向队列的头部的位置
    int last;//指向队列的尾部的下一个位置
    int n;

    public QueueForArray() {
        items= (Item[]) new Object[2];
        first=0;
        last=0;
        n=0;
    }

    //出列
    @Override
    public Item dequeue() throws Exception {
        if(isEmpty()) throw new Exception("");
        Item item=items[first];
        items[first]=null;
        first++;
        if(first==items.length) first=0;
        n--;
        if(n>0&&items.length/4==n) resize(items.length/2);
        return item;
    }

    //入列
    @Override
    public void enqueue(Item item) {
        if(n==items.length) {
            resize(2*items.length);
        }
        items[last++]=item;
        if(last==items.length) last=0;
        n++;
    }

    //扩容
    private void resize(int capacity) {
        Item[] newItems= (Item[]) new Object[capacity];
        for (int i=0;i<items.length;i++){
            newItems[i]=items[(first+i)%items.length];
        }
        items=newItems;
        first=0;
        last=n;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return n==0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item>{

        int i;

        @Override
        public boolean hasNext() {
            return i<n;
        }

        @Override
        public Item next() {
            if(!hasNext()) try {
                throw new Exception("");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Item item=items[(i+first)%items.length];
            i++;
            return item;
        }
    }

    public static void main(String[] args) {
        QueueForArray<Integer> queueForArray=new QueueForArray<Integer>();
        queueForArray.enqueue(1);
        queueForArray.enqueue(2);
        queueForArray.enqueue(3);
//        while (!queueForArray.isEmpty()){
//            try {
//                System.out.println(queueForArray.dequeue());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        Iterator<Integer> iterator = queueForArray.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
