package util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiNegative {
    String STATUS_CODE = "{statusCode}";
    String METHOD = "{method}";
    String statusCode() default "";
    String method() default "";
}
