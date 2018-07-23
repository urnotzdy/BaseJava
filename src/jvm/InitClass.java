package jvm;

import java.util.Random;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/7/11 14:16.
 */
public class InitClass {

    static {
        System.out.println("Init static block");
    }

    public static void main(String[] args) {
        System.out.println(Test1.x);
        //输出结果为：
        /*
        Init static block
        2
        */
        System.out.println(Test2.x);
        //输出结果为：
        /*
        Init static block
        Test2 static block
        -1224863071
        */
        System.out.println(Test3.x);
        //输出结果为：
        /*
        Init static block
        Test2 static block
        1808773282
        */
        System.out.println(Test2.x);
    }

}
class Test1{
    public static final int x=6/3;
    static {
        System.out.println("Test1 static block");
    }
}
class Test2{
    public static final int x=new Random().nextInt();
    static {
        System.out.println("Test2 static block");
    }
}
class Test3 extends Test2{
    static {
        System.out.println("Test3 static block");
    }
}
