package alibaba;

import org.junit.Test;

import java.time.Instant;
import java.util.*;

import static javafx.scene.input.KeyCode.T;

public class exampleTest {

    @Test
    public void oneTest() {
        String str = "a,b,c,,";
        String[] ary = str.split(","); // 预期大于 3，结果是 3
        for (String a : ary) {
            System.out.println(a);
        }
        System.out.println(ary.length);
    }

    @Test
    public void twoTest() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            String item = iterator.next();
//            if (item.equals("1")) {
//                iterator.remove();
//
//            }
//        }

        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }
        System.out.println(list);
    }

    @Test
    public void threeTest() {
         Map<String,String> map = new HashMap<>();
//         Set<String> keys=map.entrySet();
//         for(String s:keys){
//             System.out.println(s);
//         }
        Instant.now();
        Optional.empty();
    }

}
