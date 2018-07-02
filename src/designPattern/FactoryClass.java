package designPattern;

import vo.Square;

import java.lang.reflect.InvocationTargetException;

/**
 * Description:简单工厂模式
 * User: zhangdanyang
 * Date: 2018/5/24 10:26.
 */
public class FactoryClass {

    //解决增加一个产品就需要增加一个对象实现工厂的方法
    public static <T> T getClass(Class<? extends T> clazz){
        T t=null;
        try {
            t=(T)Class.forName(clazz.getName()).newInstance();
            t=clazz.getDeclaredConstructor().newInstance();
            t=clazz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static void main(String[] args){
        getClass(Square.class).toDesc();
    }
}
