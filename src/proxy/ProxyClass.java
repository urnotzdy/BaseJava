package proxy;

import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyClass {

    public static void main(String[] args) {
        IUserService userService= (IUserService) Proxy.newProxyInstance(
                IUserService.class.getClassLoader(),
                new Class[]{IUserService.class},
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("方法执行前");
                method.invoke(new UserService(),args);
                System.out.println("方法执行后");
                return null;
            }
        });
        userService.getName(1);
    }

    @Test
    public void getProxyClass() throws IOException {
        byte[] classcodes=ProxyGenerator.generateProxyClass("UserService$Proxy",new Class[]{IUserService.class});
        File file=new File("UserService$Proxy.class");
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        fileOutputStream.write(classcodes);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
