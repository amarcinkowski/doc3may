package io.github.amarcinkowski.doc3may.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(SOURCE)
@Target(METHOD)
public @interface ApiOperation {

	String value();
	String notes();
	boolean hidden() default false;
	
}
