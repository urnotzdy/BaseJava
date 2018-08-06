package Queue;

import java.util.Iterator;

//使用链表的方式实现队列
public class QueueForLinkedList<Item> implements Queue<Item>,Iterable<Item> {


    Entry<Item> first;//队头
    Entry<Item> last;//队尾
    int count;//队列中元素的个数

    public QueueForLinkedList() {
        this.first = null;
        this.last = null;
        this.count = 0;
    }

    //出栈
    @Override
    public Item dequeue() throws Exception {
        if(count==0) throw new Exception("");
        //将first节点移除一个，并将first置为null
        Entry<Item> oldFirst=first;
        first=first.next;
        //设置数量
        count--;
        if(count==0) last=null;
        return oldFirst.item;
    }

    //入栈
    @Override
    public void enqueue(Item item) {
        //新建一个节点，放到队尾
        Entry<Item> oldlast=last;
        last=new Entry<Item>(item,null);
        //如果队头为null，则队头等于队尾
        if(count==0) first=last;
        else oldlast.next=last;
        //设置数量
        count++;
    }

    //元素中的个数
    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count==0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyIterator<Item>(first);
    }

    private class MyIterator<Item> implements Iterator<Item>{

        Entry<Item> current;

        public MyIterator(Entry<Item> first) {
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public Item next() {
            if(!hasNext()) try {
                throw new Exception("");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Item item=current.item;
            current=current.next;
            return item;
        }
    }

    //节点
    private class Entry<Item>{
        Item item;
        Entry<Item> next;

        public Entry(Item item, Entry<Item> next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) throws Exception {
        Queue<Integer> queue=new QueueForLinkedList<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
//        while (!queue.isEmpty()){
//            System.out.println(queue.dequeue());
//        }
        Iterator<Integer> iterator=((QueueForLinkedList<Integer>) queue).iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
