package designPattern;

import vo.IShape;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/6/8 15:55.
 */
public class RedDecoratorClass extends DecoratorClass {

    public RedDecoratorClass(IShape ishape) {
        super(ishape);
    }

    public void toDesc(){
        super.toDesc();
        setRed();
    }

    public void setRed(){
        System.out.println("set red!");
    }

}
