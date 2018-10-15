package it.nicolagiacchetta.betfair.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotated parameter must not be <tt>null</tt> or an <tt>empty</tt> empty {@link String}.
 *
 * @author  Nicola Giacchetta
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {

}
