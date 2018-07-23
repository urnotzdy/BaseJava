package proxy;

public class UserService implements IUserService {

    @Override
    public String getName(int uid) {
        System.out.println("目标方法执行");
        return null;
    }

}
