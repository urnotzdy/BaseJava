package vo;

import designPattern.Description;

/**
 * Description:具体产品
 * User: zhangdanyang
 * Date: 2018/5/24 10:28.
 */
public class Square implements IShape {

    @Description("person")
    @Override
    public void toDesc() {
        System.out.println("Shape is square!");
    }
}
