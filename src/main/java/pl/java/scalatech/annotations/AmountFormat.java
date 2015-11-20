package pl.java.scalatech.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Target(value=FIELD)
@Retention(RUNTIME)
@Documented
public @interface AmountFormat {

}
