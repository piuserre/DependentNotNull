package com.notnull.dependent.dependentnotnull;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ FIELD })
@Retention(RUNTIME)
@Documented
public @interface NotNullDependentValue {

    String message() default "";

    String fieldName();

    String[] fieldValues();

}
