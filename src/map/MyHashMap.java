package map;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/6/29 15:56.
 */
public class MyHashMap<K,V> implements MyMap<K,V>{

    //数组,用来存放数据，默认长度为16
    MyEntry<K,V> table[]=null;
    //集合的默认长度
    private static int defaultLength=16;
    //加载因子0.75
    private static float loadFactor=0.75f;
    //记录数组的元素的个数
    int size=0;
    @Override
    public V put(K k, V v) {
        if(table==null){
            table=new MyEntry[defaultLength];
        }
        //确认下标位置
        int index=getIndex(k,defaultLength);
        //确认是不是修改
        MyMap.Entry<K,V> entry=table[index];
        while (entry!=null){
            if(entry.getKey().equals(k)){
                //修改 替换
                entry.setValue(v);
                return v;
            }else {
                entry=entry.getNext();
            }
        }
        //新增
        table[index]=new MyEntry<K, V>(k,v,table[index]);
        size++;
        //扩容
        float threshold=defaultLength*loadFactor;
        if(size>threshold){
            resize(2*defaultLength);
        }
        return v;
    }

    //扩容
    private void resize(int length) {
        MyEntry<K,V> newTable[]=new MyEntry[length];
        //重新计算index
        for (int i=0;i<table.length;i++){
            MyMap.Entry<K,V> temp=table[i];
            while (temp!=null){
                int index=getIndex(temp.getKey(),length);
                newTable[index]=new MyEntry<K, V>(temp.getKey(),temp.getValue(),newTable[index]);
                temp=temp.getNext();
            }
        }
        table=newTable;
        defaultLength=length;
        newTable=null;
    }

    int getIndex(K k,int length){
        if(k==null)
            return 0;
        return k.hashCode()&(length-1);
    }

    @Override
    public V get(K k) {
        int index=getIndex(k,defaultLength);
        MyMap.Entry<K,V> entry=table[index];
        while (entry!=null){
            if(entry.getKey().equals(k)){
                return entry.getValue();
            }else {
                entry=entry.getNext();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
//节点
class MyEntry<K,V> implements MyMap.Entry<K,V>{
    K k;
    V v;
    MyMap.Entry<K, V> next;

    public MyEntry(K k, V v, MyMap.Entry<K, V> next) {
        this.k = k;
        this.v = v;
        this.next = next;
    }

    @Override
    public K getKey() {
        return k;
    }

    @Override
    public V getValue() {
        return v;
    }

    @Override
    public V setValue(V v) {
        V old=this.v;
        this.v=v;
        return old;
    }

    @Override
    public MyMap.Entry<K, V> getNext() {
        return this.next;
    }

    @Override
    public MyMap.Entry<K, V> setNext(MyMap.Entry<K, V> next) {
        MyMap.Entry<K,V> old=this.next;
        this.next=next;
        return old;
    }
}
