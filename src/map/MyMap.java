package map;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/7/2 11:40.
 */
public interface MyMap<K,V> {

    //向集合中插入值
    public V put(K k,V v);

    //根据key获取值
    public V get(K k);

    //获取集合中元素的个数
    public int size();

    interface Entry<K,V>{
        K getKey();
        V getValue();
        V setValue(V v);
        Entry<K,V> getNext();
        Entry<K,V> setNext(Entry<K,V> next);
    }

}
