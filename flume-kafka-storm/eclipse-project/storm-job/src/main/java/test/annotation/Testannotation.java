package test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import javax.swing.text.Element;

@Target(ElementType.TYPE)
public @interface Testannotation {
	public String testClass() default "classname";
}
