package org.nnsoft.commons.meiyo.classvisitor;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;


public interface LinkedHandlingBuilder<E extends AnnotatedElement, A extends Annotation> {

    <H extends AnnotationHandler<E, A>> void withHandler(H handler);

}
