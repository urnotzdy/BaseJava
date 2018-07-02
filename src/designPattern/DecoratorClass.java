package designPattern;

import vo.IShape;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/6/8 15:46.
 */
public abstract class DecoratorClass implements IShape{

    protected IShape iShape;

    public DecoratorClass(IShape ishape){
        this.iShape=ishape;
    }

    public void toDesc() {
        iShape.toDesc();
    }
}
