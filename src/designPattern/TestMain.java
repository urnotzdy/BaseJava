package designPattern;

import vo.IShape;
import vo.Square;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/6/8 16:01.
 */
public class TestMain {

    public static void main(String[] args){
        IShape shape=new RedDecoratorClass(new Square());
        shape.toDesc();
    }

}
