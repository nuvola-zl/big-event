package com.itheima.anno;

import com.itheima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented       // 元注解

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {StateValidation.class}
)
public @interface State {

    String message() default "状态只能是已发布或者是草稿";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
