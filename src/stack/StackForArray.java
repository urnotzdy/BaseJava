package stack;

import java.util.Iterator;

//使用数组实现栈
public class StackForArray<Item> implements Stack<Item>,Iterable<Item>{

    private Item[] items;//数组
    private int n;//栈中元素个数

    public StackForArray(){
        items= (Item[]) new Object[2];
        n=0;
    }

    //压入元素
    @Override
    public void push(Item item) {
        if(n==items.length){
            //扩容
            resize(2*items.length);
        }
        items[n++]=item;
    }

    private void resize(int capacity) {
        Item[] newItem= (Item[]) new Object[capacity];
        for(int i=0;i<items.length;i++){
            newItem[i]=items[i];
        }
        items=newItem;
    }

    //返回并弹出栈顶元素
    @Override
    public Item pop() throws Exception {
        if(isEmpty()) throw new Exception("stack underflow!");
        Item item=items[n-1];
        items[n-1]=null;
        n--;
        if(n>0&&n==items.length/4) resize(items.length/2);
        return item;
    }


    //返回栈顶元素
    @Override
    public Item peek() throws Exception {
        if(isEmpty()) throw new Exception("stack underflow!");
        return items[n-1];
    }

    //是否为空
    @Override
    public boolean isEmpty() {
        return n==0;
    }

    //栈中元素的个数
    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    //从栈顶开始遍历
    private class StackIterator implements Iterator<Item>{

        private int i;

        public StackIterator(){
            i=n-1;
        }

        @Override
        public boolean hasNext() {
            return i>=0;
        }

        @Override
        public Item next() {
            if(!hasNext()) try {
                throw new Exception("");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return items[i--];
        }
    }

    public static void main(String[] args){
        Stack<Integer> stack=new StackForArray<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("length:"+stack.size());
//        while (!stack.isEmpty()) {
//            try {
//                System.out.println(stack.pop());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        Iterator<Integer> iterator = ((StackForArray<Integer>) stack).iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
