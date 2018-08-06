package stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//使用链表的方式实现栈
public class StaskForLinkedList<Item> implements Stack<Item>,Iterable<Item>{

    Entry<Item> first;//栈顶指针
    int n;//元素个数

    public StaskForLinkedList() {
        first=null;
        n=0;
    }

    @Override
    public void push(Item item) {
        Entry<Item> oldfirst=first;
        first=new Entry<>(item,oldfirst);
        n++;
    }

    @Override
    public Item pop() throws Exception {
        if(n==0) throw new Exception("");
        Item item=first.item;
        first=first.next;
        n--;
        return item;
    }

    @Override
    public Item peek() throws Exception {
        if(n==0) throw new Exception("");
        return first.item;
    }

    @Override
    public boolean isEmpty() {
        return n==0;
//        return first==null;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new StackLinedListIterator(first);
    }


    private class StackLinedListIterator implements Iterator<Item>{

        Entry<Item> currrent;

        public StackLinedListIterator(Entry<Item> currrent) {
            this.currrent = currrent;
        }

        @Override
        public boolean hasNext() {
            return currrent!=null;
        }

        @Override
        public Item next() {
            if(!hasNext()) try {
                throw new Exception("");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Item item=currrent.item;
            currrent=currrent.next;
            return item;
        }
    }

    class Entry<Item>{
        Item item;//当前节点的值
        Entry<Item> next;//下一个节点的指针

        public Entry(Item item, Entry<Item> next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) throws Exception {
        StaskForLinkedList<Integer> staskForLinkedList=new StaskForLinkedList<>();
        staskForLinkedList.push(1);
        staskForLinkedList.push(2);
//        while (!staskForLinkedList.isEmpty()){
//            System.out.println(staskForLinkedList.pop());
//        }
        Iterator<Integer> iterator = staskForLinkedList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
