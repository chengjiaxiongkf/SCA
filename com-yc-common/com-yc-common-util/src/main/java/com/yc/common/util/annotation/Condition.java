package com.yc.common.util.annotation;

import com.yc.common.util.enums.ConditionEnum;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author sangang
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Condition {

    ConditionEnum condition() default ConditionEnum.EQ;

    String field() default "";

    String sql() default "";

}
