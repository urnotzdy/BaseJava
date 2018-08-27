package DateStructure;

/**
 * Description:链表反转---递归法
 * User: zhangdanyang
 * Date: 2018/8/27 11:40.
 */
public class LinkedListReverse {

    public static void main(String[] args){
        Node old=readyNode();
        Node newNode=foreach(old);
        while (newNode!=null){
            System.out.println(newNode.data);
            newNode=newNode.next;
        }
    }

    //遍历法
    /**
     * 需要一个指针，保留遍历过的数值
     * 临时节点保存当前节点的next节点
     * */
    public static Node foreach(Node n){
        Node nowNode=null;//获取当前节点
        Node tempNode=null;//
        while (n!=null){
            tempNode=n.next;
            n.next=nowNode;
            nowNode=n;
            n=tempNode;
        }
        return nowNode;
    }

    //递归法
    public static Node reverse(Node n){
        if(n==null||n.next==null){
            return n;
        }
        Node head=reverse(n.next);
        n.next.next=n;
        n.next=null;
        return head;
    }

    static class Node {
        Integer data;
        Node next;
    }

    static Node readyNode() {
        Node linkNode1 = new Node();
        linkNode1.data = 1;
        Node linkNode2 = new Node();
        linkNode2.data = 2;
        Node linkNode3 = new Node();
        linkNode3.data = 3;
        Node linkNode4 = new Node();
        linkNode4.data = 4;
        Node linkNode5 = new Node();
        linkNode5.data = 5;
        Node linkNode6 = new Node();
        linkNode6.data = 6;
        linkNode1.next = linkNode2;
        linkNode2.next = linkNode3;
        linkNode3.next = linkNode4;
        linkNode4.next = linkNode5;
        linkNode5.next = linkNode6;
        return linkNode1;
    }

}
