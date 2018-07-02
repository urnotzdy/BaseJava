package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description:缓存系统的设计。
 * 当集合较大，读的次数大于写的次数时，可以使用读写锁
 * User: zhangdanyang
 * Date: 2018/6/14 10:40.
 */
public class CacheReadWriteLockClass {
    Map<String,Object> map=new HashMap<String,Object>();
    ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    public void get(String key){
        readWriteLock.readLock().lock();//读锁
        Object value=null;
        try{
            value=map.get(key);
            if(value==null){
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                //查询数据
                if(value==null) {//避免一个写线程写完后，后面的线程还继续写
                    value = "12121";
                    map.put(key, value);
                }
                readWriteLock.readLock().lock();
                readWriteLock.writeLock().unlock();
            }
        }finally {
            readWriteLock.readLock().unlock();
        }
    }

}
