package stack;

public interface Stack<Item> {

    //压入元素
    void push(Item item);

    //弹出并返回元素
    Item pop() throws Exception;

    //返回但不弹出元素
    Item peek() throws Exception;

    //是否为空
    boolean isEmpty();

    //返回栈的个数
    int size();

}
