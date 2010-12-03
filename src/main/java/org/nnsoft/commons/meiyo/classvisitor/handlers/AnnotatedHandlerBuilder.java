package org.nnsoft.commons.meiyo.classvisitor.handlers;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

public interface AnnotatedHandlerBuilder<E extends AnnotatedElement> {

    <A extends Annotation> LinkedHandlingBuilder<E, A> annotatedWith(Class<A> annotationType);

}
