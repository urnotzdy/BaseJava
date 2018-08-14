package map;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/7/23 9:54.
 */
public interface IMyMap<K,V> {
    //增加
    public V put(K k,V v);
    //查找
    public V get(K k);
    //删除
    public void remove(K k);
    //获取元素个数
    public void size();
    //节点对象
    interface Entry<K,V>{
        K getKey();
        V getValue();
        V setValue(V v);
        Entry<K,V> getNext();
        Entry<K,V> setNext(Entry<K,V> next);
    }
}
