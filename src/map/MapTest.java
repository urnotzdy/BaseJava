package map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/7/2 16:22.
 */
public class MapTest {

    @Test
    public void test(){
//        MyHashMap<String,String> map=new MyHashMap<>();
        Map<String,String> map1=new HashMap<>();
        MyHashMap_1<String,String> map=new MyHashMap_1<String,String>();
        map.put("12","map12");
        map.put("2","map2");
        map.put("31","map31");
        map.put("1","map1");
        map.put("21","map21");
        map.put("32","map32");
        map.put("13","map13");
        map.put("23","map23");
        map.put("3","map3");
        map.put("14","map14");
        map.put("24","map24");
        map.put("34","map34");
        map.put("15","map15");
        map.put("25","map25");
        map.put("35","map35");
        map.put("16","map16");
        map.put("26","map26");
        map.put("3","map32");
        System.out.println(map.get("3"));
    }

}
