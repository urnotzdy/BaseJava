package map;

/**
 * Description:手写map
 * User: zhangdanyang
 * Date: 2018/7/23 9:52.
 */
public class MyHashMap_1<K,V> implements IMyMap<K,V>{

    //数组
    MyEntry<K,V> table[]=null;
    //加载因子
    private static float loadFactor=0.75f;
    //默认长度
    private static int defaultLength=16;
    //集合长度
    int size;

    @Override
    public V put(K k, V v) {
        if(null == table){
            table=new MyEntry[defaultLength];
        }
        //计算下标
        int index=getIndex(k);
        //放数据
        Entry<K,V> entry=table[index];
        while (entry!=null){
            if(entry.getKey().equals(k)){
                entry.setValue(v);
                return v;
            }else{
                entry=entry.getNext();
            }
        }
        table[index]=new MyEntry<>(k,v,table[index]);
        //扩容
        if(size>defaultLength*loadFactor){
            resize();
        }
        size++;
        return v;
    }

    //得到数组下标
    private int getIndex(K k) {
        return k.hashCode()&(defaultLength-1);
    }

    //扩容
    private void resize() {
        defaultLength=defaultLength*2;//扩容
        MyEntry<K,V> newTable[]=new MyEntry[defaultLength];
        //将原数组数据复制到新数组
        for(int i=0;i<table.length;i++){
            MyEntry<K,V> entry=table[i];
            while (entry!=null){
                MyHashMap_1.MyEntry oldNext= (MyEntry) entry.next;//保留原下节点
                //移到新数组中
                int index=getIndex(entry.getKey()); //重新计算下标
                newTable[index]=new MyEntry<K,V>(entry.getKey(),entry.getValue(),newTable[index]);
                entry=oldNext;
            }
        }
        table=newTable;
        newTable=null;
    }

    @Override
    public V get(K k) {
        int index=getIndex(k);
        Entry<K,V> entry=table[index];
        while(entry!=null){
            if(k.equals(entry.getKey())){
                return entry.getValue();
            }else{
                entry=entry.getNext();
            }
        }
        return null;
    }

    @Override
    public void remove(K k) {

    }

    @Override
    public void size() {

    }

    class MyEntry<K,V> implements Entry<K,V>{
        K k;
        V v;
        Entry<K,V> next;

        public MyEntry(K k, V v, Entry<K, V> next) {
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
        public Entry<K, V> getNext() {
            return next;
        }

        @Override
        public Entry<K, V> setNext(Entry<K, V> next) {
            Entry<K,V> old=this.next;
            this.next=next;
            return old;
        }
    }

}
