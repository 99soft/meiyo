package org.nnsoft.commons.meiyo.classpath.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention( RetentionPolicy.CLASS )
@Target( ElementType.TYPE )
public @interface NoRuntimeRetention
{

    String value();

}
