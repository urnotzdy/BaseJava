package designPattern;

import java.lang.annotation.*;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/5/25 14:27.
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description {

    String value();
}
